package com.grument.bleconsole.injection;

import android.content.ContentResolver;
import android.content.Context;

import com.grument.bleconsole.app.BleConsoleApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class BleConsoleAppModule {

    private final BleConsoleApp bleConsoleApp;

    public BleConsoleAppModule(BleConsoleApp bleConsoleApp) {
        this.bleConsoleApp = bleConsoleApp;
    }

    @Provides
    BleConsoleApp provideBleConsoleApp() {
        return bleConsoleApp;
    }

    @ApplicationContext
    @Provides
    Context provideContext() {
        return bleConsoleApp;
    }


    @Singleton
    @Provides
    ContentResolver provideContentResolver(@ApplicationContext Context context) {
        return context.getContentResolver();
    }


}
