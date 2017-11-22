package com.example.joseph.bluetoothle;

import android.bluetooth.BluetoothDevice;

/**
 * Created by joseph on 11/22/17.
 */

public class BLEDevice {
    BluetoothDevice device;
    int rssi;

    public BLEDevice(BluetoothDevice device, int rssi) {
        this.device = device;
        this.rssi = rssi;
    }
}
