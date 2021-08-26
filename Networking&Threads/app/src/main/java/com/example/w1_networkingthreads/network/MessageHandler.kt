package com.example.w1_networkingthreads.network

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

private const val TAG = "MessageHandler"

class MessageHandler(
    looper: Looper,
    private val onDataReceived: (string: String) -> Unit
) : Handler(looper) {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        onDataReceived(msg.toString())
    }

}