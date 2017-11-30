package com.grument.bleconsole.activity.find.di;

import android.content.Context;

import com.grument.bleconsole.activity.find.FindBleDeviceActivity;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityPresenter;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityPresenterImpl;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityView;
import com.grument.bleconsole.adapter.BleDeviceItemAdapter;
import com.grument.bleconsole.injection.ActivityContext;
import com.grument.bleconsole.injection.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class FindBleDeviceActivityModule {

    private final FindBleDeviceActivity findBleDeviceActivity;
    private final FindBleDeviceActivityView findBleDeviceActivityView;

    public FindBleDeviceActivityModule(FindBleDeviceActivity findBleDeviceActivityView) {
        this.findBleDeviceActivity = findBleDeviceActivityView;
        this.findBleDeviceActivityView = findBleDeviceActivityView;
    }

    @ActivityContext
    @Provides
    Context provideContext() {
        return findBleDeviceActivity;
    }

    @ActivityScope
    @Provides
    FindBleDeviceActivityView provideFindBleDeviceActivityView() {
        return findBleDeviceActivityView;
    }

    @ActivityScope
    @Provides
    BleDeviceItemAdapter provideBleDeviceItemAdapter(FindBleDeviceActivityPresenter findBleDeviceActivityPresenter) {
        return new BleDeviceItemAdapter(findBleDeviceActivityPresenter);
    }

    @ActivityScope
    @Provides
    FindBleDeviceActivityPresenter provideFindBleDeviceActivityPresenter(FindBleDeviceActivityView findBleDeviceActivity) {
        return new FindBleDeviceActivityPresenterImpl(findBleDeviceActivity);
    }

}
