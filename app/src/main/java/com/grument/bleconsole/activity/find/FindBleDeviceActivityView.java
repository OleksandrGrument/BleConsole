package com.grument.bleconsole.activity.find;


import android.bluetooth.BluetoothDevice;

import com.grument.bleconsole.activity.base.BaseView;


public interface FindBleDeviceActivityView extends BaseView {

    void showNewBleDevice(BluetoothDevice bluetoothDevice);

    void showProgress();

    void hideProgress();

}
