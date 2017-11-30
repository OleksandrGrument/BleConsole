package com.grument.bleconsole.fragment;

import android.app.DialogFragment;
import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.console.ConsoleActivityView;
import com.grument.bleconsole.adapter.BleCharacteristicItemAdapter;

import java.util.ArrayList;


public class CharacteristicFragmentDialog extends DialogFragment {


    private final static String BLE_CHARACTERISTIC_KEY = "BLE_CHARACTERISTIC_KEY";

    private ArrayList<BluetoothGattCharacteristic> bluetoothGattCharacteristics;

    public static CharacteristicFragmentDialog newInstance(ArrayList<BluetoothGattCharacteristic> bluetoothGattCharacteristics) {

        CharacteristicFragmentDialog characteristicFragmentDialog = new CharacteristicFragmentDialog();
        Bundle args = new Bundle();
        args.putSerializable(BLE_CHARACTERISTIC_KEY, bluetoothGattCharacteristics);
        characteristicFragmentDialog.setArguments(args);

        return characteristicFragmentDialog;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bluetoothGattCharacteristics = (ArrayList<BluetoothGattCharacteristic>) getArguments().getSerializable(BLE_CHARACTERISTIC_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View characteristicFragmentDialogView = inflater.inflate(R.layout.fragment_dialog_characteristic, null);
        RecyclerView characteristicsRecyclerView = characteristicFragmentDialogView.findViewById(R.id.rv_find_characteristics);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        BleCharacteristicItemAdapter bleServiceItemAdapter = new BleCharacteristicItemAdapter(bluetoothGattCharacteristics, (ConsoleActivityView) getActivity());
        characteristicsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        characteristicsRecyclerView.setAdapter(bleServiceItemAdapter);

        return characteristicFragmentDialogView;
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }


}