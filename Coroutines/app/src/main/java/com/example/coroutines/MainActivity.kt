package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.math.floor
import kotlin.math.roundToLong

private const val TAG = "ConsoleMain|| "

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        runCoroutines()
    }

    private fun runCoroutines() {
        val account = Account();

        withTimeMeasurement("Single coroutine deposit 1000 times") {
            runBlocking {
                launch {
                    for (i in 1..1000)
                        account.deposit(0.0)
                }
            }
        }

        withTimeMeasurement("Two coroutines together") {
            /*
               Create an instance of Mutex, which can be used to protect critical sections in
               function blocks inside of a coroutine.
             */
            val mutex = Mutex()

            runBlocking {
                launch {
                    // Access the deposit function with lock
                    for (i in 1..1000) mutex.withLock { account.deposit(1.0) }
                }
                launch {
                    // Access the deposit function with lock
                    for (i in 1..1000) mutex.withLock { account.deposit(1.0) }
                }
            }
            // Set text after above block completes
            binding.txtTotal.text = getString(R.string.total_str, account.getTotal().toString())
        }
    }


    /* Approximate measurement of the given block's execution time */
    private fun withTimeMeasurement(title: String, isActive: Boolean = true, code: () -> Unit) {
        if (!isActive) return
        val timeStart = System.currentTimeMillis()
        code()
        val timeEnd = System.currentTimeMillis()
        println(TAG + "operation in '$title' took ${(timeEnd - timeStart)} ms")
    }

}


class Account {
    private var amount: Double = 0.0

    suspend fun deposit(amount: Double) {
        val x = this.amount
        delay(1)
        this.amount = x + amount
    }

    fun getTotal(): Double = amount
}