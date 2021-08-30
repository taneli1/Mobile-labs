package com.example.internalsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.internalsensor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check permissions here
        val sensorManager: SensorManager =
            getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) !== null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ProximitySensorFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
        } else {
            // No sensor found..
            binding.txtNoSensor.visibility = View.VISIBLE
        }
    }
}