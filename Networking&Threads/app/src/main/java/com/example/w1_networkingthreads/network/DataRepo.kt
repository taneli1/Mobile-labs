package com.example.w1_networkingthreads.network

import android.os.Looper

object DataRepo {

    fun fetchTextData(handler: SimpleMessageHandler) {
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