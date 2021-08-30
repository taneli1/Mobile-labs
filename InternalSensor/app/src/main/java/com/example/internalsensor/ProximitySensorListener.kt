package com.example.internalsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ProximitySensorListener(
    context: Context,
    private val coroutineScope: CoroutineScope
) : SensorEventListener {

    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val sensor =
        sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    val distanceInCm = MutableLiveData(0f)

    // ... Public methods ...

    // Call to start
    fun registerListener() {
        sensorManager.registerListener(
            this,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    // Call to stop
    fun unregisterListener() {
        sensorManager.unregisterListener(this)
    }

    // ... Overrides ...

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return
        coroutineScope.launch {
            val distanceToObj = event.values[0]
            distanceInCm.postValue(distanceToObj)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println("AccuracyChanged")
    }
}