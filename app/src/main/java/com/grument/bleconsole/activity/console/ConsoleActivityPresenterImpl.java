package com.grument.bleconsole.activity.console;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import com.grument.bleconsole.R;
import com.grument.bleconsole.event.CharacteristicChooseEvent;
import com.grument.bleconsole.event.CharacteristicReadEvent;
import com.grument.bleconsole.event.DisconnectEvent;
import com.grument.bleconsole.event.SendCommandEvent;
import com.grument.bleconsole.event.ServiceDiscoveredEvent;
import com.grument.bleconsole.event.StartServiceDiscoveryEvent;
import com.grument.bleconsole.util.BleAttributeNameFindUtil;
import com.grument.bleconsole.util.ConsoleMessageGeneratorUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;


public class ConsoleActivityPresenterImpl implements ConsoleActivityPresenter {

    private ConsoleActivityView consoleActivityView;

    private static int chooseCharacteristicType;

    private ConsoleMessageGeneratorUtil consoleMessageGeneratorUtil;

    public ConsoleActivityPresenterImpl(ConsoleActivityView consoleActivityView) {
        this.consoleActivityView = consoleActivityView;
    }

    @Override
    public void onCreated() {
        consoleMessageGeneratorUtil = new ConsoleMessageGeneratorUtil(consoleActivityView.getContext());
    }

    @Override
    public void onStarted() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStopped() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setCharacteristic(int type) {
        chooseCharacteristicType = type;
        EventBus.getDefault().post(new StartServiceDiscoveryEvent());
    }

    @Override
    public void chooseCharacteristic(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        EventBus.getDefault().post(new CharacteristicChooseEvent(bluetoothGattCharacteristic, chooseCharacteristicType));

        String bleConsoleMessage = consoleMessageGeneratorUtil
                .generateSetCharacteristicSuccessMessage(
                        chooseCharacteristicType,
                        bluetoothGattCharacteristic.getUuid());

        consoleActivityView.showBleConsoleMessage(bleConsoleMessage);
    }

    @Override
    public void sendCommand(String command) {

        byte[] byteConsoleCommand = BleAttributeNameFindUtil.parseStringConsoleCommand(command);

        if (byteConsoleCommand == null)
            consoleActivityView.showWarning(R.string.invalid_command_warning);
        else {
            EventBus.getDefault().post(new SendCommandEvent(byteConsoleCommand));

            String bleConsoleMessage = consoleMessageGeneratorUtil
                    .generateSendCommandMessage(byteConsoleCommand);
            consoleActivityView.showBleConsoleMessage(bleConsoleMessage);
        }

    }


    @Subscribe
    public void onServiceDiscovered(ServiceDiscoveredEvent serviceDiscoveredEvent) {
        ArrayList<BluetoothGattService> bluetoothGattServices = (ArrayList<BluetoothGattService>) serviceDiscoveredEvent.getBluetoothGattServices();
        consoleActivityView.showServiceFragment(bluetoothGattServices);

    }

    @Subscribe
    public void onCharacteristicReadEvent(CharacteristicReadEvent characteristicReadEvent) {
        String bleConsoleMessage = consoleMessageGeneratorUtil
                .generateReadCharacteristicMessage(characteristicReadEvent.getCharacteristicMessage());
        consoleActivityView.showBleConsoleMessage(bleConsoleMessage);

    }

    @Subscribe
    public void onDeviceDisconnected(DisconnectEvent disconnectEvent) {
        consoleActivityView.showWarning(R.string.disconnection_warning);
        consoleActivityView.goBackOnDisconnect();
    }

}
