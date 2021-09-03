package com.example.bluetoothbeacon

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bluetoothbeacon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: BLEViewModel
    private lateinit var bleAdapter: BLEAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        setDefaultNightMode(MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupStuff()

        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bleAdapter
        }

        setupClickListeners()
        setContentView(binding.root)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setupStuff() {
        vm = ViewModelProvider(this).get(MainViewModel::class.java)
        bleAdapter = BLEAdapter(vm)

        vm.devices.observe(this) {
            bleAdapter.notifyDataSetChanged()
        }
    }

    private fun setupClickListeners() {
        binding.btn.setOnClickListener {
            val enabled = permissions()
            if (enabled) {
                vm.beacon.scanDevices()
            }
        }
    }

    private fun permissions(): Boolean {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1);
            return false
        }
        return true
    }
}