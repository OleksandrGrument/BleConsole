package com.grument.bleconsole.activity.console.di;


import com.grument.bleconsole.activity.console.ConsoleActivity;
import com.grument.bleconsole.injection.ActivityScope;
import com.grument.bleconsole.injection.BleConsoleAppComponent;

import dagger.Component;

@ActivityScope
@Component(dependencies = BleConsoleAppComponent.class, modules = ConsoleActivityModule.class)
public interface ConsoleActivityComponent {

    void injectActivity(ConsoleActivity consoleActivity);

}

