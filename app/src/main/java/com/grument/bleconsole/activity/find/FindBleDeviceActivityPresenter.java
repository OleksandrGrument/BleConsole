package com.grument.bleconsole.activity.find;

import com.grument.bleconsole.activity.base.BasePresenter;


public interface FindBleDeviceActivityPresenter extends BasePresenter {

    void onCreated();

    void onResumed();

    void onPaused();

    void startNewDeviceDiscovery();

    void connectToDevice(String deviceAddress);

}