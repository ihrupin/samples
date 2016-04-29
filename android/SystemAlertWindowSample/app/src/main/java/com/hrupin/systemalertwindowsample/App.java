package com.hrupin.systemalertwindowsample;

import android.app.Application;

/**
 * Created by Igor Khrupin www.hrupin.com on 4/29/16.
 */
public class App extends Application{

    private static App INSTANCE = null;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static App getInstance() {
        return INSTANCE;
    }

    public void showButton() {
        new OverlayButton(this).attachToWindow();
    }
}

