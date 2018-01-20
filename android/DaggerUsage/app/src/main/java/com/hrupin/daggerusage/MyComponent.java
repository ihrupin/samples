package com.hrupin.daggerusage;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Igor Khrupin www.hrupin.com on 1/20/18.
 */

@Singleton
@Component(modules = SharedPreferencesModule.class)
public interface MyComponent {
    void inject(MainActivity mainActivity);
}
