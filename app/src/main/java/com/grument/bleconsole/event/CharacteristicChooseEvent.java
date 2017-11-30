package com.grument.bleconsole.event;


import android.bluetooth.BluetoothGattCharacteristic;

public class CharacteristicChooseEvent {

    private final BluetoothGattCharacteristic bluetoothGattCharacteristic;

    private final int type;

    public CharacteristicChooseEvent(BluetoothGattCharacteristic bluetoothGattCharacteristic, int type) {
        this.bluetoothGattCharacteristic = bluetoothGattCharacteristic;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public BluetoothGattCharacteristic getBluetoothGattCharacteristic() {
        return bluetoothGattCharacteristic;
    }

    @Override
    public String toString() {
        return "CharacteristicChooseEvent{" +
                "bluetoothGattCharacteristic=" + bluetoothGattCharacteristic +
                ", type=" + type +
                '}';
    }
}
