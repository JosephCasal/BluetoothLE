package com.example.joseph.bluetoothle;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph on 11/22/17.
 */

public class LeDeviceListAdapter extends RecyclerView.Adapter<LeDeviceListAdapter.ViewHolder>{

    private static final String TAG = "LeDeviceListAdapter";
    List<BLEDevice> devices = new ArrayList<>();
    Context context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.device_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BLEDevice d = devices.get(position);
        BluetoothDevice device = d.device;

        holder.device = device;

        holder.tvName.setText( device.getName() );
        holder.tvAddress.setText( device.getAddress() );
        holder.tvRSSI.setText(String.valueOf(d.rssi));

    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    public void addDevice(BluetoothDevice device, int rssi){
        devices.add(new BLEDevice(device, rssi));
        notifyDataSetChanged();
        Log.d(TAG, "addDevice: " + devices.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAddress;
        TextView tvRSSI;
        BluetoothDevice device;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvRSSI = itemView.findViewById(R.id.tvRSSI);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DeviceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("device", device);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

        }
    }

}
