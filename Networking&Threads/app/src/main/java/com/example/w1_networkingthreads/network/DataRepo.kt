package com.example.w1_networkingthreads.network

import android.os.Looper

object DataRepo {

    private val looper = Looper.getMainLooper()

    fun fetchTextData(handler: MessageHandler, onDataReceived: (string:String) -> Unit) {
        if (isNetworkAvailable()) {
            val myRunnable = Connection(
                handler
            )
            val myThread = Thread(myRunnable)
            myThread.start()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return true
    }
}