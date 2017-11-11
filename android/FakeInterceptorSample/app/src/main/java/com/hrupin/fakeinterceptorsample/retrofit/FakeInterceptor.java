package com.hrupin.fakeinterceptorsample.retrofit;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 *
 */
public class FakeInterceptor implements Interceptor {
    private static final String TAG = FakeInterceptor.class.getSimpleName();
    private static final String FILE_EXTENSION = ".json";
    private Context mContext;

    private String mContentType = "application/json";

    public FakeInterceptor(Context context) {
        mContext = context;
    }

    /**
     * Set content type for header
     *
     * @param contentType Content type
     * @return FakeInterceptor
     */
    public FakeInterceptor setContentType(String contentType) {
        mContentType = contentType;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        List<String> listSuggestionFileName = new ArrayList<>();
        String method = chain.request().method().toLowerCase();

        Response response = null;
        // Get Request URI.
        final URI uri = chain.request().url().uri();
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString());

        String defaultFileName = getFileName(chain);

        if (defaultFileName != null) {
            String fileName = getFilePath(uri, defaultFileName);
            Log.d(TAG, "Read data from file: " + fileName);
            try {
                InputStream is = mContext.getAssets().open(fileName);
                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                StringBuilder responseStringBuilder = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    responseStringBuilder.append(line).append('\n');
                }
                Log.d(TAG, "Response: " + responseStringBuilder.toString());
                Response.Builder builder = new Response.Builder();
                builder.request(chain.request());
                builder.protocol(Protocol.HTTP_1_0);
                builder.addHeader("content-type", mContentType);

                builder.body(ResponseBody.create(MediaType.parse(mContentType), responseStringBuilder.toString().getBytes()));
                builder.code(200);
                builder.message(responseStringBuilder.toString());

                response = builder.build();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        } else {
            for (String file : listSuggestionFileName) {
                Log.e(TAG, "File not exist: " + getFilePath(uri, file));
            }
            response = chain.proceed(chain.request());
        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString());
        return response;
    }

    private String getFileName(Chain chain) {

        String method = chain.request().method();
        if (method.equals("POST")) {
            String fileName = null;
            String lastSegment = chain.request().url().pathSegments().get(chain.request().url().pathSegments().size() - 1);

            if (lastSegment.equals("login")) {
                fileName = "login";
            } else {
                fileName = lastSegment;
            }

            return fileName + FILE_EXTENSION;
        }
        return "none" + FILE_EXTENSION;
    }

    private String getFilePath(URI uri, String fileName) {
        String path;
        if (uri.getPath().lastIndexOf('/') != uri.getPath().length() - 1) {
            path = uri.getPath().substring(0, uri.getPath().lastIndexOf('/') + 1);
        } else {
            path = uri.getPath();
        }
        return uri.getHost() + path + fileName;
    }
}
