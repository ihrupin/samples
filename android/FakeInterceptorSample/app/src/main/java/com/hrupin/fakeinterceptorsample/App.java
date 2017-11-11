package com.hrupin.fakeinterceptorsample;

import android.app.Application;

/**
 * Created by Igor Khrupin www.hrupin.com on 11/11/17.
 */

public class App extends Application {
    private static App INSTANCE;

    public static App getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
