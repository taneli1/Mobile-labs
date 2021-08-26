package com.example.w1_networkingthreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import com.example.w1_networkingthreads.network.DataRepo
import com.example.w1_networkingthreads.network.MessageHandler
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    fun test(strin:String): Unit{

    }

    //private val handler = MessageHandler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}