package com.grument.bleconsole.activity.find.di;


import com.grument.bleconsole.activity.find.FindBleDeviceActivity;
import com.grument.bleconsole.injection.ActivityScope;
import com.grument.bleconsole.injection.BleConsoleAppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = BleConsoleAppComponent.class, modules = FindBleDeviceActivityModule.class)
public interface FindBleDeviceActivityComponent {

    void injectActivity(FindBleDeviceActivity findBleDeviceActivity);

}
