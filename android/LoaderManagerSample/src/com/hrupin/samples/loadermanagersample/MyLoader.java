package com.hrupin.samples.loadermanagersample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class MyLoader extends AsyncTaskLoader<String>{
		private String mData;
		private String url;

		public MyLoader(Context context, String url) {
			super(context);
			this.url = url;
		}

		@Override
		public String loadInBackground() {
			String data = null;
			try {
				InputStream input = new URL(url).openStream();
				data = getContent(input);
			} catch (IOException e) {
				return e.getMessage();
			} 
			
			if(data == null){
				return "NULL Data";
			}
			return data;
		}
		
		private static String getContent(InputStream in) throws IOException {
			InputStreamReader is = new InputStreamReader(in);
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(is);
			String read = br.readLine();

			while (read != null) {
				sb.append(read);
				read = br.readLine();

			}

			return sb.toString();
		}
		@Override
		public void deliverResult(String data) {
			mData = data;

			if (isStarted()) {
				super.deliverResult(data);
			}
		}

		@Override
		protected void onStartLoading() {
			if (mData != null) {
				deliverResult(mData);
			}
			
			if (takeContentChanged() || mData == null) {
				forceLoad();
			}
		}

		@Override
		protected void onStopLoading() {
			cancelLoad();
		}
}
