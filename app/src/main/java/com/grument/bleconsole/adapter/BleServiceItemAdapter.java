package com.grument.bleconsole.adapter;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grument.bleconsole.R;
import com.grument.bleconsole.activity.console.ConsoleActivityView;
import com.grument.bleconsole.util.BleAttributeNameFindUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BleServiceItemAdapter extends RecyclerView.Adapter<BleServiceItemAdapter.ViewHolder> {


    private List<BluetoothGattService> bluetoothGattServices;

    private ConsoleActivityView consoleActivityView;

    public BleServiceItemAdapter(List<BluetoothGattService> bluetoothGattServices, ConsoleActivityView consoleActivityView) {
        this.bluetoothGattServices = bluetoothGattServices;
        this.consoleActivityView = consoleActivityView;
    }

    @Override
    public BleServiceItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_ble_service, parent, false);
        return new BleServiceItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BleServiceItemAdapter.ViewHolder holder, int position) {

        final BluetoothGattService bluetoothGattService = bluetoothGattServices.get(position);

        final ArrayList<BluetoothGattCharacteristic> bluetoothGattCharacteristics = (ArrayList<BluetoothGattCharacteristic>) bluetoothGattService.getCharacteristics();

        UUID uuid = bluetoothGattService.getUuid();

        holder.bleServiceUuidTextView.setText(uuid.toString());

        holder.bleServiceNameTextView.setText(BleAttributeNameFindUtil.getNameFromServiceUuid(uuid));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consoleActivityView.showCharacteristicFragment(bluetoothGattCharacteristics);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bluetoothGattServices.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_ble_card_service_name)
        TextView bleServiceNameTextView;

        @BindView(R.id.tv_ble_card_service_uuid)
        TextView bleServiceUuidTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
