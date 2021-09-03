package com.example.bluetoothbeacon

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.sign

class BLEAdapter(
    private val vm: BLEViewModel
) : RecyclerView.Adapter<BLEAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.device_name)
        private val address = itemView.findViewById<TextView>(R.id.mac_address)
        private val signal = itemView.findViewById<TextView>(R.id.signal_strength)
        private val connectable = itemView.findViewById<TextView>(R.id.connectable)

        @SuppressLint("SetTextI18n")
        fun bindData(device: SimpleBLEDevice) {
            name.text = device.name ?: "--"
            address.text = device.address ?: "--"
            signal.text = "${device.signal} dBm"
            connectable.text = "Connectable: ${device.connectable}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BLEAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_ble_device, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vm.devices.value?.count() ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        vm.devices.value?.get(position).let {
            if (it != null) {
                holder.bindData(it)
            }
        }
    }
}