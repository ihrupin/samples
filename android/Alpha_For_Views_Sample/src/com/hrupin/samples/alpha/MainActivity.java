package com.hrupin.samples.alpha;

import com.hrupin.samples.halfopacitywidgets.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button buttonWithAlpha;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonWithAlpha = (Button)findViewById(R.id.buttonWithOpacity);
		
		setAlphaForView(buttonWithAlpha, 0.5f);
	}

	private void setAlphaForView(View v, float alpha) {
		AlphaAnimation animation = new AlphaAnimation(alpha, alpha);
		animation.setDuration(0); 
		animation.setFillAfter(true); 
		v.startAnimation(animation);
	}
}
