package com.grument.bleconsole.activity.console.di;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Handler;

import com.grument.bleconsole.activity.console.ConsoleActivity;
import com.grument.bleconsole.activity.console.ConsoleActivityPresenter;
import com.grument.bleconsole.activity.console.ConsoleActivityPresenterImpl;
import com.grument.bleconsole.activity.console.ConsoleActivityView;
import com.grument.bleconsole.activity.find.FindBleDeviceActivity;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityPresenter;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityPresenterImpl;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityView;
import com.grument.bleconsole.adapter.ConsoleItemAdapter;
import com.grument.bleconsole.injection.ActivityContext;
import com.grument.bleconsole.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ConsoleActivityModule {

    private final ConsoleActivity consoleActivity;

    public ConsoleActivityModule(ConsoleActivity consoleActivity) {
        this.consoleActivity = consoleActivity;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return consoleActivity;
    }

    @ActivityScope
    @Provides
    ConsoleActivityView provideConsoleActivityView() {
        return consoleActivity;
    }

    @ActivityScope
    @Provides
    ConsoleItemAdapter provideConsoleItemAdapter() {
        return new ConsoleItemAdapter();
    }

    @ActivityScope
    @Provides
    Handler provideMessageHandler() {
        return new Handler();
    }

    @ActivityScope
    @Provides
    ConsoleActivityPresenter provideConsoleActivityPresenter(ConsoleActivityView consoleActivityView) {
        return new ConsoleActivityPresenterImpl(consoleActivityView);
    }

}
