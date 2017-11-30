package com.grument.bleconsole.activity.console;


import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.base.BaseActivity;
import com.grument.bleconsole.activity.console.di.ConsoleActivityModule;

import com.grument.bleconsole.activity.console.di.DaggerConsoleActivityComponent;
import com.grument.bleconsole.adapter.ConsoleItemAdapter;
import com.grument.bleconsole.app.BleConsoleApp;
import com.grument.bleconsole.event.StartCharacteristicReadEvent;
import com.grument.bleconsole.fragment.CharacteristicFragmentDialog;
import com.grument.bleconsole.fragment.FaqFragmentDialog;
import com.grument.bleconsole.fragment.ServiceFragmentDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.grument.bleconsole.util.Constants.READ_CHARACTERISTIC;
import static com.grument.bleconsole.util.Constants.WRITE_CHARACTERISTIC;

public class ConsoleActivity extends BaseActivity implements ConsoleActivityView {

    @BindView(R.id.fl_fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.rv_console)
    RecyclerView consoleRecyclerView;

    @BindView(R.id.et_input_commands)
    EditText inputCommandsEditText;

    @Inject
    ConsoleActivityPresenter consoleActivityPresenter;

    @Inject
    ConsoleItemAdapter consoleItemAdapter;

    @Inject
    Handler messageHandler;

    ServiceFragmentDialog serviceFragmentDialog;

    CharacteristicFragmentDialog characteristicFragmentDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);
        ButterKnife.bind(this);
        injectActivity();

        consoleActivityPresenter.onCreated();

        consoleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        consoleRecyclerView.setAdapter(consoleItemAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        consoleActivityPresenter.onStarted();
    }

    @Override
    public void onStop() {
        super.onStop();
        consoleActivityPresenter.onStopped();
    }


    @OnClick(R.id.bt_set_read_characteristic)
    public void setReadCharacteristic() {
        consoleActivityPresenter.setCharacteristic(READ_CHARACTERISTIC);
    }

    @OnClick(R.id.bt_set_write_characteristic)
    public void setWriteCharacteristic() {
        consoleActivityPresenter.setCharacteristic(WRITE_CHARACTERISTIC);
    }

    @OnClick(R.id.bt_read_characteristic)
    public void readCharacteristic() {
        EventBus.getDefault().post(new StartCharacteristicReadEvent());
    }

    @OnClick(R.id.bt_send_command)
    public void sendCommandButton() {
        consoleActivityPresenter.sendCommand(inputCommandsEditText.getText().toString());
    }

    @Override
    public void showServiceFragment(ArrayList<BluetoothGattService> bluetoothGattServices) {
        serviceFragmentDialog = ServiceFragmentDialog.newInstance(bluetoothGattServices);
        serviceFragmentDialog.show(getFragmentManager().beginTransaction(), "ServiceFragmentDialog");
    }

    @Override
    public void showCharacteristicFragment(ArrayList<BluetoothGattCharacteristic> bluetoothGattCharacteristics) {
        serviceFragmentDialog.dismiss();
        characteristicFragmentDialog = CharacteristicFragmentDialog.newInstance(bluetoothGattCharacteristics);
        characteristicFragmentDialog.show(getFragmentManager().beginTransaction(), "CharacteristicFragmentDialog");
    }

    @Override
    public void enableSendAndShowCharacteristicChosen(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        characteristicFragmentDialog.dismiss();
        consoleActivityPresenter.chooseCharacteristic(bluetoothGattCharacteristic);
    }

    @Override
    public void showBleConsoleMessage(String message) {
        consoleItemAdapter.notifyAndShow(message);
        consoleRecyclerView.smoothScrollToPosition(consoleItemAdapter.getItemCount() - 1);
    }

    @Override
    public void goBackOnDisconnect() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_faq, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FaqFragmentDialog faqFragmentDialog = FaqFragmentDialog.newInstance();
        faqFragmentDialog.show(getFragmentManager().beginTransaction(), "FaqFragmentDialog");

        return super.onOptionsItemSelected(item);
    }

    void injectActivity() {
        DaggerConsoleActivityComponent.builder()
                .bleConsoleAppComponent(((BleConsoleApp) getApplication()).getBleConsoleAppComponent())
                .consoleActivityModule(new ConsoleActivityModule(this))
                .build()
                .injectActivity(this);
    }


    @Override
    public void showWarning(final int warningMessageResourceId) {
        Runnable doDisplayError = new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), getString(warningMessageResourceId),
                        Toast.LENGTH_LONG).show();
            }
        };
        messageHandler.post(doDisplayError);

    }

    @Override
    public Context getContext() {
        return this;
    }
}
