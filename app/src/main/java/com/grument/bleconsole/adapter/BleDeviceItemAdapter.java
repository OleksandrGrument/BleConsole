package com.grument.bleconsole.adapter;


import android.bluetooth.BluetoothDevice;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.find.FindBleDeviceActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BleDeviceItemAdapter extends RecyclerView.Adapter<BleDeviceItemAdapter.ViewHolder> {


    private List<BluetoothDevice> bluetoothDevices;

    private FindBleDeviceActivityPresenter findBleDeviceActivityPresenter;

    public BleDeviceItemAdapter(FindBleDeviceActivityPresenter findBleDeviceActivityPresenter) {
        this.findBleDeviceActivityPresenter = findBleDeviceActivityPresenter;
        bluetoothDevices = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_ble_device, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BluetoothDevice bluetoothDevice = bluetoothDevices.get(position);

        final String deviceAddress = bluetoothDevice.getAddress();
        final String deviceName = bluetoothDevice.getName();

        holder.bleDeviceNameTextView.setText(deviceName);

        holder.bleDeviceAddressTextView.setText(deviceAddress);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findBleDeviceActivityPresenter.connectToDevice(deviceAddress);
            }
        });
    }


    public void notifyAndShow(BluetoothDevice bluetoothDevice) {
        bluetoothDevices.add(bluetoothDevice);
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    public void clearAll() {
        bluetoothDevices.clear();
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return bluetoothDevices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ble_card_device_name)
        TextView bleDeviceNameTextView;

        @BindView(R.id.tv_ble_card_device_address)
        TextView bleDeviceAddressTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
