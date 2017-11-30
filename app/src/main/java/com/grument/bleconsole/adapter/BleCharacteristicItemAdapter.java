package com.grument.bleconsole.adapter;


import android.bluetooth.BluetoothGattCharacteristic;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.console.ConsoleActivityView;
import com.grument.bleconsole.util.BleAttributeNameFindUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BleCharacteristicItemAdapter extends RecyclerView.Adapter<BleCharacteristicItemAdapter.ViewHolder> {


    private List<BluetoothGattCharacteristic> bluetoothGattCharacteristics;

    private ConsoleActivityView consoleActivityView;

    public BleCharacteristicItemAdapter(List<BluetoothGattCharacteristic> bluetoothGattCharacteristics, ConsoleActivityView consoleActivityView) {
        this.bluetoothGattCharacteristics = bluetoothGattCharacteristics;
        this.consoleActivityView = consoleActivityView;
    }

    @Override
    public BleCharacteristicItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_ble_characteristic, parent, false);
        return new BleCharacteristicItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BleCharacteristicItemAdapter.ViewHolder holder, int position) {

        final BluetoothGattCharacteristic bluetoothGattCharacteristic = bluetoothGattCharacteristics.get(position);

        holder.bleCharacteristicUuidTextView.setText(bluetoothGattCharacteristic.getUuid().toString());

        holder.bleCharacteristicPropertyTextView.setText(BleAttributeNameFindUtil.getNameFromPropertyId(bluetoothGattCharacteristic.getProperties()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consoleActivityView.enableSendAndShowCharacteristicChosen(bluetoothGattCharacteristic);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bluetoothGattCharacteristics.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ble_card_characteristic_uuid)
        TextView bleCharacteristicUuidTextView;

        @BindView(R.id.tv_ble_card_characteristic_property)
        TextView bleCharacteristicPropertyTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

