package com.grument.bleconsole.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Intent;


import com.grument.bleconsole.BuildConfig;
import com.grument.bleconsole.injection.BleConsoleAppComponent;
import com.grument.bleconsole.injection.BleConsoleAppModule;
import com.grument.bleconsole.injection.DaggerBleConsoleAppComponent;
import com.grument.bleconsole.service.BluetoothLeService;



import timber.log.Timber;

import static android.os.Build.VERSION_CODES.LOLLIPOP_MR1;


public class BleConsoleApp extends Application {


    private BleConsoleAppComponent bleConsoleAppComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        injectApplication();
        plantTimber();
        startService(new Intent(this, BluetoothLeService.class));
    }


    @TargetApi(LOLLIPOP_MR1)
    private void plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }


    private void injectApplication() {

        bleConsoleAppComponent = DaggerBleConsoleAppComponent
                .builder()
                .bleConsoleAppModule(new BleConsoleAppModule(this))
                .build();

        bleConsoleAppComponent.injectApplication(this);
    }

    public BleConsoleAppComponent getBleConsoleAppComponent() {
        return bleConsoleAppComponent;
    }


}
