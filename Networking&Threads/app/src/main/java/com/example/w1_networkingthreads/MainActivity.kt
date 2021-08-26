package com.example.w1_networkingthreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.example.w1_networkingthreads.databinding.ActivityMainBinding
import com.example.w1_networkingthreads.network.DataRepo
import com.example.w1_networkingthreads.network.SimpleMessageHandler

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val handler = SimpleMessageHandler(Looper.getMainLooper()) {
        updateUI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fetch text
        DataRepo.fetchTextData(handler)
    }

    private fun updateUI(string: String){
        val textView = binding.txtData
        textView.text = string
    }
}