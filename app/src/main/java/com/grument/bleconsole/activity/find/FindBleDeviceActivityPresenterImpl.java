package com.grument.bleconsole.activity.find;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;



import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.console.ConsoleActivity;
import com.grument.bleconsole.event.ConnectEvent;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

import static com.grument.bleconsole.util.Constants.ACTION_GATT_CONNECTED;
import static com.grument.bleconsole.util.Constants.BLE_DEVICE;

public class FindBleDeviceActivityPresenterImpl implements FindBleDeviceActivityPresenter {

    private final FindBleDeviceActivityView findBleDeviceActivityView;

    private BroadcastReceiver bleReceiver;

    private BluetoothAdapter bluetoothAdapter;

    private Context context;

    public FindBleDeviceActivityPresenterImpl(FindBleDeviceActivityView findBleDeviceActivityView) {
        this.findBleDeviceActivityView = findBleDeviceActivityView;
    }

    @Override
    public void onCreated() {
        init();
    }


    @Override
    public void onResumed() {

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(ACTION_GATT_CONNECTED);
        context.registerReceiver(bleReceiver, filter);

    }

    @Override
    public void onPaused() {
        context.unregisterReceiver(bleReceiver);
    }

    @Override
    public void startNewDeviceDiscovery() {

        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.cancelDiscovery();
            bluetoothAdapter.startDiscovery();

        } else {
            findBleDeviceActivityView.showWarning(R.string.bluetooth_turned_off_warning);
        }
    }

    @Override
    public void connectToDevice(String deviceAddress) {
        EventBus.getDefault().post(new ConnectEvent(deviceAddress));
        findBleDeviceActivityView.showWarning(R.string.connecting);
    }

    private void init() {

        context = findBleDeviceActivityView.getContext();

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bleReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String action = intent.getAction();

                Timber.i("action - %s", action);

                assert action != null;
                switch (action) {
                    case BluetoothDevice.ACTION_FOUND:

                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        Timber.i("BLE Device name + %s", device.getName());

                        if (device.getType() != BluetoothDevice.DEVICE_TYPE_CLASSIC)
                            findBleDeviceActivityView.showNewBleDevice(device);
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                        Timber.i("Discovery started");

                        findBleDeviceActivityView.showProgress();
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                        Timber.i("Discovery finished");

                        findBleDeviceActivityView.hideProgress();
                        break;
                    case ACTION_GATT_CONNECTED:
                        Timber.i( "Gatt connected success");

                        Intent startConsoleActivityIntent = new Intent(context, ConsoleActivity.class);
                        startConsoleActivityIntent.putExtra(BLE_DEVICE, intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE));
                        context.startActivity(startConsoleActivityIntent);
                        break;

                }
            }
        };
    }
}
