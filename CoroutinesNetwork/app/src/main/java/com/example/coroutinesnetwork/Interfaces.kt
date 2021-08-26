package com.example.coroutinesnetwork

import android.graphics.Bitmap
import android.media.Image
import androidx.lifecycle.MutableLiveData

interface ImageFetchParameters {
    val url: String
}

interface ImageViewModel {
    val currentImage: MutableLiveData<Bitmap>
    fun fetchImage(params: ImageFetchParameters)
}