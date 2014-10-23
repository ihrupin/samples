package com.hrupin.samples.loadermanagersample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {

	private ViewPager tabPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabPager = (ViewPager) findViewById(R.id.tabPager);
		tabPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
	}
}
