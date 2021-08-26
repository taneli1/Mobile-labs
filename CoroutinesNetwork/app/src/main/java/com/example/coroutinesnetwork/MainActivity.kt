package com.example.coroutinesnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinesnetwork.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeState()
        startImageFetch()
    }

    private fun observeState() {
        val imageView = binding.imageView

        // When a bitmap is ready, set it to imageview
        viewModel.currentImage.observe(this) {
            imageView.setImageBitmap(it)
        }
    }

    private fun startImageFetch() {
        val params = object : ImageFetchParameters {
            override val url = "https://placekitten.com/1080/1500"
        }
        viewModel.fetchImage(params)
    }

}