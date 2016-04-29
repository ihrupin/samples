package com.hrupin.edu.androidEx2.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hrupin.edu.androidEx2.R;

/**
 * Created by Igor Khrupin www.hrupin.com on 1/20/16.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Button buttonVideoSample;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);

        buttonVideoSample = (Button) findViewById(R.id.buttonVideoSample);
        buttonVideoSample.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.buttonVideoSample) {
            // **********************************
            // HERE SET YOUR VIDEO URI
            String video_uri = "http://www.hrupin.com/wp-content/uploads/2016/01/sample_video.3gp";
            // HERE SET YOUR VIDEO URI
            // For example: http://www.hrupin.com/wp-content/uploads/2016/01/sample_video.3gp
            // **********************************

            Intent intent = new Intent(this, VideoSample.class);
            intent.putExtra("video_path", video_uri);
            startActivity(intent);
        }
    }
}
