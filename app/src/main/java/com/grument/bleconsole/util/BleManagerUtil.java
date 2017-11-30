package com.grument.bleconsole.util;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;

import com.grument.bleconsole.event.CharacteristicReadEvent;
import com.grument.bleconsole.event.DisconnectEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import timber.log.Timber;

import static com.grument.bleconsole.util.Constants.ACTION_GATT_CONNECTED;


public class BleManagerUtil {

    public BleManagerUtil(Context context) {
        this.context = context;
    }

    private Context context;

    private BluetoothGatt mBluetoothGatt;

    private BluetoothGattCharacteristic readCharacteristic;
    private BluetoothGattCharacteristic writeCharacteristic;


    // Various callback methods defined by the BLE API.
    private final BluetoothGattCallback mGattCallback =

            new BluetoothGattCallback() {

                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                    int newState) {

                    Intent intentAction = new Intent(ACTION_GATT_CONNECTED);

                    switch (newState) {
                        case BluetoothProfile.STATE_CONNECTED:

                            context.sendBroadcast(intentAction);
                            Timber.i("Connected to GATT server.");
                            Timber.i("Attempting to start service discovery:" + mBluetoothGatt.discoverServices());

                            break;
                        case BluetoothProfile.STATE_DISCONNECTED:
                            EventBus.getDefault().post(new DisconnectEvent());
                            Timber.i("Disconnected from GATT server.");
                            break;

                    }

                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {

                        Timber.i("SUCCESS SERVICE DISCOVERED");

                        List<BluetoothGattService> bluetoothGattServices = gatt.getServices();

                        for (BluetoothGattService bluetoothGattService : bluetoothGattServices) {

                            Timber.i("SERVICE UUID" + bluetoothGattService.getUuid().toString());

                            List<BluetoothGattCharacteristic> bluetoothGattCharacteristics = bluetoothGattService.getCharacteristics();

                            for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattCharacteristics) {

                                Timber.i("-----CHARACTERISTICS UUID " + bluetoothGattCharacteristic.getUuid());
                                Timber.i("-----CHARACTERISTICS WRITE TYPE " + bluetoothGattCharacteristic.getWriteType());
                                Timber.i("-----CHARACTERISTICS PROPERTIES " + bluetoothGattCharacteristic.getProperties());
                                Timber.i("-----CHARACTERISTICS PERMISSIONS " + bluetoothGattCharacteristic.getPermissions());

                                List<BluetoothGattDescriptor> bluetoothGattDescriptors = bluetoothGattCharacteristic.getDescriptors();

                                for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattDescriptors) {

                                    Timber.i("----------DESCRIPTOR UUID" + bluetoothGattDescriptor.getUuid());
                                    Timber.i("----------DESCRIPTOR PERMISSIONS" + bluetoothGattDescriptor.getPermissions());

                                }
                            }
                        }
                    } else {
                        Timber.i("onServicesDiscovered received: " + status);
                    }
                }


                @Override
                public void onCharacteristicRead(BluetoothGatt gatt,
                                                 BluetoothGattCharacteristic characteristic,
                                                 int status) {

                    Timber.i("On characteristic");
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        EventBus.getDefault().post(new CharacteristicReadEvent(readCharacteristic.getValue()));
                    }
                }


                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic) {
                    // this will get called anytime you perform a read or write characteristic operation
                    Timber.i("GATonCharacteristicChanged");

                }

                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt,
                                                  BluetoothGattCharacteristic characteristic, int status) {
                    Timber.i("onCharacteristicWrite method gets called. status " + Integer.toString(status));
                }

                @Override
                public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    Timber.i("onDescriptorRead Read method gets called.");
                }


                @Override
                public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        Timber.i("Callback: Success writing GATT Descriptor: " + status);
                    } else {
                        Timber.i("Callback: Error writing GATT Descriptor: " + status);
                    }
                }

            };


    public List<BluetoothGattService> getDeviceServices() {
        return mBluetoothGatt.getServices();
    }


    public void connectToDevice(BluetoothDevice selectedDevice) {
        mBluetoothGatt = selectedDevice.connectGatt(context, false, mGattCallback);
    }

    public void setWriteCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        writeCharacteristic = bluetoothGattCharacteristic;
    }

    public void setReadCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        mBluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
        readCharacteristic = bluetoothGattCharacteristic;
    }

    public void readCharacteristic() {
        mBluetoothGatt.readCharacteristic(readCharacteristic);
    }

    public void sendCommand(byte command[]) {
        writeCharacteristic.setValue(command);
        mBluetoothGatt.writeCharacteristic(writeCharacteristic);
    }


}
