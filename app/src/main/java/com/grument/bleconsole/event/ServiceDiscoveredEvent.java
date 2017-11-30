package com.grument.bleconsole.event;


import android.bluetooth.BluetoothGattService;

import java.util.List;

public class ServiceDiscoveredEvent {

    private final List<BluetoothGattService> bluetoothGattServices;

    public ServiceDiscoveredEvent(List<BluetoothGattService> bluetoothGattServices) {
        this.bluetoothGattServices = bluetoothGattServices;
    }

    public List<BluetoothGattService> getBluetoothGattServices() {
        return bluetoothGattServices;
    }

    @Override
    public String toString() {
        return "ServiceDiscoveredEvent{" +
                "bluetoothGattServices=" + bluetoothGattServices +
                '}';
    }
}
