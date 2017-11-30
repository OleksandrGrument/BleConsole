package com.grument.bleconsole.activity.find;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.base.BaseActivity;
import com.grument.bleconsole.activity.find.di.DaggerFindBleDeviceActivityComponent;
import com.grument.bleconsole.activity.find.di.FindBleDeviceActivityModule;
import com.grument.bleconsole.adapter.BleDeviceItemAdapter;
import com.grument.bleconsole.app.BleConsoleApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindBleDeviceActivity extends BaseActivity implements FindBleDeviceActivityView {

    @BindView(R.id.rv_find_ble_devices)
    RecyclerView findBledDevicesRecyclerView;

    @BindView(R.id.pb_discovery)
    ProgressBar discoveryProgressBar;

    @BindView(R.id.tv_start_discovery)
    TextView startDiscoveryTextView;

    @Inject
    FindBleDeviceActivityPresenter findBleDeviceActivityPresenter;

    @Inject
    BleDeviceItemAdapter bleDeviceItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_ble_device);
        ButterKnife.bind(this);
        injectActivity();

        findBledDevicesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        findBledDevicesRecyclerView.setAdapter(bleDeviceItemAdapter);

        findBleDeviceActivityPresenter.onCreated();
    }

    @Override
    public void onResume() {
        super.onResume();
        findBleDeviceActivityPresenter.onResumed();
    }

    @Override
    public void onPause() {
        super.onPause();
        findBleDeviceActivityPresenter.onPaused();
    }

    @OnClick(R.id.bt_discovery_devices)
    public void startDiscoveryDevices() {
        bleDeviceItemAdapter.clearAll();
        findBleDeviceActivityPresenter.startNewDeviceDiscovery();
    }

    @Override
    public void showNewBleDevice(BluetoothDevice bluetoothDevice) {
        bleDeviceItemAdapter.notifyAndShow(bluetoothDevice);
    }

    @Override
    public void showProgress() {
        discoveryProgressBar.setVisibility(View.VISIBLE);
        startDiscoveryTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        discoveryProgressBar.setVisibility(View.INVISIBLE);
        startDiscoveryTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showWarning(int warningMessageResourceId) {
        Toast.makeText(this, getResources().getString(warningMessageResourceId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    void injectActivity() {
        DaggerFindBleDeviceActivityComponent.builder()
                .bleConsoleAppComponent(((BleConsoleApp) getApplication()).getBleConsoleAppComponent())
                .findBleDeviceActivityModule(new FindBleDeviceActivityModule(this))
                .build()
                .injectActivity(this);
    }

}
