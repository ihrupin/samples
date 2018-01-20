package com.hrupin.daggerusage;

import android.content.SharedPreferences;

import javax.inject.Inject;

/**
 * Created by Igor Khrupin www.hrupin.com on 1/20/18.
 */

public class MySharedPreferences {
    private SharedPreferences mSharedPreferences;

    @Inject
    public MySharedPreferences(SharedPreferences mSharedPreferences) {
        this.mSharedPreferences = mSharedPreferences;
    }

    public void putData(String key, int data) {
        mSharedPreferences.edit().putInt(key,data).apply();
    }

    public int getData(String key) {
        return mSharedPreferences.getInt(key,0);
    }
}
