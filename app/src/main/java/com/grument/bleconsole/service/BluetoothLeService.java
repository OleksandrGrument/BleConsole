package com.grument.bleconsole.service;


import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.grument.bleconsole.event.CharacteristicChooseEvent;
import com.grument.bleconsole.event.ConnectEvent;
import com.grument.bleconsole.event.SendCommandEvent;
import com.grument.bleconsole.event.ServiceDiscoveredEvent;
import com.grument.bleconsole.event.StartCharacteristicReadEvent;
import com.grument.bleconsole.event.StartServiceDiscoveryEvent;
import com.grument.bleconsole.util.BleManagerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import timber.log.Timber;

import static com.grument.bleconsole.util.Constants.READ_CHARACTERISTIC;
import static com.grument.bleconsole.util.Constants.WRITE_CHARACTERISTIC;


public class BluetoothLeService extends Service {


    private BluetoothAdapter mBluetoothAdapter;
    private BleManagerUtil bleManagerUtil;

    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

        Timber.i("Service on create");

        bleManagerUtil = new BleManagerUtil(this);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    }

    @Subscribe
    public void onConnectEvent(ConnectEvent connectEvent) {
        BluetoothDevice bleDevice = mBluetoothAdapter.getRemoteDevice(connectEvent.getDeviceAddress());
        bleManagerUtil.connectToDevice(bleDevice);
    }

    @Subscribe
    public void onServiceDiscoveryEvent(StartServiceDiscoveryEvent startServiceDiscoveryEvent) {
        List<BluetoothGattService> bluetoothGattServices = bleManagerUtil.getDeviceServices();
        EventBus.getDefault().post(new ServiceDiscoveredEvent(bluetoothGattServices));
    }

    @Subscribe
    public void onCharacteristicChooseEvent(CharacteristicChooseEvent characteristicChooseEvent) {

        switch (characteristicChooseEvent.getType()) {
            case READ_CHARACTERISTIC:
                bleManagerUtil.setReadCharacteristic(characteristicChooseEvent.getBluetoothGattCharacteristic());
                break;
            case WRITE_CHARACTERISTIC:
                bleManagerUtil.setWriteCharacteristic(characteristicChooseEvent.getBluetoothGattCharacteristic());
                break;

        }
    }


    @Subscribe
    public void onReadEvent(StartCharacteristicReadEvent startCharacteristicReadEvent) {
        bleManagerUtil.readCharacteristic();
    }


    @Subscribe
    public void onSendCommandEvent(SendCommandEvent sendCommandEvent) {
        bleManagerUtil.sendCommand(sendCommandEvent.getCommand());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}













