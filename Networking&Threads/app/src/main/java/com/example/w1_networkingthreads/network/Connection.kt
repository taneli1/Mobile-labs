package com.example.w1_networkingthreads.network

import android.os.Handler
import android.util.Log
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val TAG = "Connection"
private const val URL = "https://users.metropolia.fi/~jarkkov/koe.txt"

class Connection(private val messageHandler: Handler) : Runnable {

    override fun run() {
        try {
            val connection = URL(URL).openConnection() as HttpsURLConnection
            val textData = connection.inputStream.bufferedReader().use {
                it.readText()
            }

            sendMessage(textData)
        } catch (e: Error) {
            Log.d(TAG, e.message.toString())
        }
    }

    private fun sendMessage(text: String) {
        val strBuilder = StringBuilder()
        val str = strBuilder.append(text).toString()

        val message = messageHandler.obtainMessage().apply {
            this.obj = str
        }

        messageHandler.sendMessage(message)
    }
}