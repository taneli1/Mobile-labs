package com.example.coroutinesnetwork

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL

class NetworkOperations {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    // Get image file in provided coroutine scope
    suspend fun getImage(url: String): Bitmap {
        return withContext(Dispatchers.IO) {
            val connection = URL(url).openConnection()
            val imageData = connection.inputStream

            return@withContext handleBitMapStream(imageData)
        }
    }

    private fun handleBitMapStream(data: InputStream): Bitmap {
        return BitmapFactory.decodeStream(data)
    }
}