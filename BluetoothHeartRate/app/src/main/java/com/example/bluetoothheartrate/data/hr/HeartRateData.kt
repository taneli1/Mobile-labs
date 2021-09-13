package com.example.bluetoothheartrate.hr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class HeartRateData(
    private var readings: MutableList<Int> = mutableListOf()
) {
    var latestValue: MutableLiveData<Int> = MutableLiveData(0)
        private set

    fun logHeartRate(value: Int) {
        latestValue.value = value
        readings.add(value)
    }

    fun getHeartRates(): List<Int> {
        return readings
    }
}