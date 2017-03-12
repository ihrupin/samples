package com.hrupin.samples.androidgeofencessample;

import android.app.Application;
import android.content.Context;

/**
 * Created by Igor Khrupin www.hrupin.com on 3/12/17.
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
