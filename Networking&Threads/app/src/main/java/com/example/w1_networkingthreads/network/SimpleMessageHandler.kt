package com.example.w1_networkingthreads.network

import android.os.Handler
import android.os.Looper
import android.os.Message

class SimpleMessageHandler(
    looper: Looper,
    private val onDataReceived: (string: String) -> Unit
) : Handler(looper) {

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        onDataReceived(msg.obj.toString())
    }
}