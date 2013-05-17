package com.hrupin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class SampleActivity extends Activity implements OnClickListener, AnimationListener {
	/** Called when the activity is first created. */

	private Animation popupShow;
	private Animation popupHide;

	private LinearLayout linearLayoutPopup;
	private Button buttonShowHidePopup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		popupShow = AnimationUtils.loadAnimation(this, R.anim.popup_show);
		popupShow.setAnimationListener(this);
		popupHide = AnimationUtils.loadAnimation(this, R.anim.popup_hide);
		popupHide.setAnimationListener(this);

		linearLayoutPopup = (LinearLayout) findViewById(R.id.linearLayoutPopUp);
		linearLayoutPopup.setVisibility(View.GONE);

		buttonShowHidePopup = (Button) findViewById(R.id.buttonShowHidePopUp);
		buttonShowHidePopup.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (linearLayoutPopup.getVisibility() == View.GONE) {
			linearLayoutPopup.startAnimation(popupShow);
		} else {
			linearLayoutPopup.startAnimation(popupHide);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation.equals(popupShow)) {
			buttonShowHidePopup.setText(getString(R.string.btn_hide_txt));
		} else if (animation.equals(popupHide)) {
			buttonShowHidePopup.setText(getString(R.string.btn_show_txt));
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		if (animation.equals(popupShow)) {
			linearLayoutPopup.setVisibility(View.VISIBLE);
		} else if (animation.equals(popupHide)) {
			linearLayoutPopup.setVisibility(View.GONE);
		}
	}
}