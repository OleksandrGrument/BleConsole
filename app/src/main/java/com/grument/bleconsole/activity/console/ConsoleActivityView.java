package com.grument.bleconsole.activity.console;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import com.grument.bleconsole.activity.base.BaseView;

import java.util.ArrayList;


public interface ConsoleActivityView extends BaseView {

    void showServiceFragment(ArrayList<BluetoothGattService> bluetoothGattServices);

    void showCharacteristicFragment(ArrayList<BluetoothGattCharacteristic> bluetoothGattCharacteristics);

    void enableSendAndShowCharacteristicChosen(BluetoothGattCharacteristic bluetoothGattCharacteristic);

    void showBleConsoleMessage(String message);

    void goBackOnDisconnect();

}
