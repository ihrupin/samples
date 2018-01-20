package com.hrupin.daggerusage;

import android.app.Application;

/**
 * Created by Igor Khrupin www.hrupin.com on 1/20/18.
 */

public class MyApplication extends Application {

    private MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        myComponent = DaggerMyComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .build();
    }

    public MyComponent getMyComponent() {
        return myComponent;
    }

}
