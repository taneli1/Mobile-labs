package com.example.bluetoothbeacon

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.Handler
import android.os.Looper

interface SimpleBLEDevice {
    val name: String?;
    val address: String?;
    val signal: Int?;
    val connectable: Boolean?;
}


// Stops scanning after 10 seconds.
private const val SCAN_PERIOD: Long = 10000

class BLEBeacon(
    private val onDeviceFound: (data: SimpleBLEDevice) -> Unit
) {
    private val handler = Handler(Looper.getMainLooper())
    private val cb = CallBack()
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val scanner = bluetoothAdapter?.bluetoothLeScanner
    private var scanning = false

    inner class CallBack : ScanCallback() {
        // Keep found devices in here, and ignore them if duplicate
        private val discoveredMacs: MutableList<String> = mutableListOf()

        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            val device = result.device

            if (discoveredMacs.contains(device.address)) {
                return
            }

            discoveredMacs.add(device.address)

            onDeviceFound(object : SimpleBLEDevice {
                override val name = device?.name
                override val address = device?.address
                override val signal = result.rssi
                override val connectable = result.isConnectable
            })
        }
    }

    fun scanDevices() {
        if (!scanning) { // Stops scanning after a pre-defined scan period.
            handler.postDelayed({
                scanning = false
                scanner?.stopScan(cb)
            }, SCAN_PERIOD)
            scanning = true
            scanner?.startScan(cb)
        } else {
            scanning = false
            scanner?.stopScan(cb)
        }
    }

}