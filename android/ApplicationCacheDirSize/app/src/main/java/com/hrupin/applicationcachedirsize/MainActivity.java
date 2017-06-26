package com.hrupin.applicationcachedirsize;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonFillCache;
    private Button buttonGetCacheSize;
    private TextView textViewSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonFillCache = (Button)findViewById(R.id.buttonFillCache);
        buttonFillCache.setOnClickListener(this);
        buttonGetCacheSize = (Button)findViewById(R.id.buttonGetCacheSize);
        buttonGetCacheSize.setOnClickListener(this);
        textViewSize = (TextView)findViewById(R.id.textViewSize);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonFillCache.getId()){
            new AddFileToCacheTask(this).run();
        }else if(v.getId() == buttonGetCacheSize.getId()){
            long sizeOfCacheDir = getCacheDirSize(getCacheDir());
            textViewSize.setText("Cache dir size = " + sizeOfCacheDir + " bytes");
        }
    }

    private long getCacheDirSize(File file) {
        long size = 0;
        for (File f : file.listFiles()) {
            if (f.listFiles() == null) {
                size = size + f.length();
            } else {
                size = size + getCacheDirSize(f);
            }
        }
        return size;
    }

    private class AddFileToCacheTask implements Runnable {

        private final Context context;

        public AddFileToCacheTask(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < 1000; i++){
                builder.append("Hello World ");
            }
            try {
                if(context != null) {
                    File file = new File(context.getCacheDir(), System.currentTimeMillis() + ".txt");
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(builder.toString());
                    bw.close();
                }
            } catch (IOException e) {
                Log.e("Exception", "File write failed: " + e.toString());
            }
        }
    }
}
