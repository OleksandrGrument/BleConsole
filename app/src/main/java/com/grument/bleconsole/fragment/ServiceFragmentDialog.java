package com.grument.bleconsole.fragment;

import android.app.DialogFragment;
import android.bluetooth.BluetoothGattService;
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
import com.grument.bleconsole.adapter.BleServiceItemAdapter;

import java.util.ArrayList;
import java.util.List;


public class ServiceFragmentDialog extends DialogFragment {

    private final static String BLE_SERVICE_KEY = "BLE_SERVICE_KEY";

    private List<BluetoothGattService> bluetoothGattServices;

    public static ServiceFragmentDialog newInstance(ArrayList<BluetoothGattService> bluetoothGattServices) {

        ServiceFragmentDialog serviceFragmentDialog = new ServiceFragmentDialog();
        Bundle args = new Bundle();
        args.putSerializable(BLE_SERVICE_KEY, bluetoothGattServices);
        serviceFragmentDialog.setArguments(args);

        return serviceFragmentDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bluetoothGattServices = (List<BluetoothGattService>) getArguments().getSerializable(BLE_SERVICE_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View serviceFragmentDialogView = inflater.inflate(R.layout.fragment_dialog_service, null);
        RecyclerView servicesRecyclerView = serviceFragmentDialogView.findViewById(R.id.rv_find_service);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        BleServiceItemAdapter bleServiceItemAdapter = new BleServiceItemAdapter(bluetoothGattServices, (ConsoleActivityView) getActivity());
        servicesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        servicesRecyclerView.setAdapter(bleServiceItemAdapter);

        return serviceFragmentDialogView;
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }


}
