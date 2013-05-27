package com.hrupin.edu.androidEx2;

import com.hrupin.edu.androidEx2.video.VideoSample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelloAndroidActivity extends Activity implements OnClickListener {

	private static String TAG = "androidEx2";

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
			String video_uri = "VIDEO_URI";
			// HERE SET YOUR VIDEO URI
			// For example: http://www.pocketjourney.com/downloads/pj/video/famous.3gp
			// **********************************
			
			Intent intent = new Intent(this, VideoSample.class);
			intent.putExtra("video_path", video_uri);
			startActivity(intent);
		}
	}

}
