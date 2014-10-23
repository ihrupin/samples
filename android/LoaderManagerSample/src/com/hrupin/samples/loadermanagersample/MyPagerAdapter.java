package com.hrupin.samples.loadermanagersample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

	private static String[] urls = { "http://www.hrupin.com",
			"http://www.hrupin.com/portfolio",
			"http://www.hrupin.com/contacts", "http://www.hrupin.com/blog",
			"http://www.hrupin.com/archives", "http://www.hrupin.com",
			"http://www.hrupin.com/portfolio",
			"http://www.hrupin.com/contacts", "http://www.hrupin.com/blog",
			"http://www.hrupin.com/archives" };

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Bundle args = new Bundle();
		args.putString(MyFragment.EXTRA_URL, urls[position]);
		MyFragment fragment = new MyFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		return urls.length;
	}
}
