package com.grument.bleconsole.injection;


import android.content.Context;

import com.grument.bleconsole.app.BleConsoleApp;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BleConsoleAppModule.class)
public interface BleConsoleAppComponent {


    void injectApplication(BleConsoleApp bleConsoleApp);

    @ApplicationContext
    Context applicationContext();

}
