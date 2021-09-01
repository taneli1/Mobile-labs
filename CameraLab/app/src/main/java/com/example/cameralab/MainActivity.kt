package com.example.cameralab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.cameralab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var takePicture: () -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        takePicture = CameraManager().setupCamera(this) { bitmap ->
            binding.img.setImageBitmap(bitmap)
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btn.setOnClickListener {
            takePicture()
        }
    }

}