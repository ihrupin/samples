package com.hrupin;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class SecondTabActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		TabActivitySampleActivity.getInstance().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		TabActivitySampleActivity.getInstance().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
