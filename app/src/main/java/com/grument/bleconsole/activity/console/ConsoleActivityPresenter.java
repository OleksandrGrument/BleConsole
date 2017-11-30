package com.grument.bleconsole.activity.console;


import android.bluetooth.BluetoothGattCharacteristic;

import com.grument.bleconsole.activity.base.BasePresenter;

public interface ConsoleActivityPresenter extends BasePresenter {

    void onCreated();

    void onStarted();

    void onStopped();

    void setCharacteristic(int type);

    void chooseCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void sendCommand(String command);

}
