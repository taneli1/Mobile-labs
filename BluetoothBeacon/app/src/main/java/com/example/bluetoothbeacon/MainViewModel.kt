package com.example.bluetoothbeacon

import androidx.lifecycle.*

interface BLEViewModel {
    val devices: MutableLiveData<MutableList<SimpleBLEDevice>>
    val beacon: BLEBeacon
}


class MainViewModel : ViewModel(), BLEViewModel {

    override val devices: MutableLiveData<MutableList<SimpleBLEDevice>> =
        MutableLiveData(mutableListOf())

    override val beacon = BLEBeacon() {
        val copy = mutableListOf(it)
        devices.value?.forEach { device ->
            copy.add(device)
        }
        devices.value = copy
        println(devices.value?.count())
    }
}