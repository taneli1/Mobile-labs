package com.example.coroutinesnetwork

import android.content.Context
import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL

private const val TAG = "MainViewModel || "

class MainViewModel : ImageViewModel, ViewModel() {
    private val networkOperations = NetworkOperations()
    override val currentImage: MutableLiveData<Bitmap> = MutableLiveData()

    override fun fetchImage(params: ImageFetchParameters) {
        viewModelScope.launch {
            try {
                val bitMap = networkOperations.getImage(params.url)
                currentImage.postValue(bitMap)
            } catch (e: Exception) {
                println(e)
                // Set errors to UI
            }
        }
    }

}