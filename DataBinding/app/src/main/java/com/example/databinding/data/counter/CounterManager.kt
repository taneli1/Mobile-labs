package com.example.databinding.data

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay


class CounterManager(
    private val counter: Counter,
    val incrementValue: MutableLiveData<Int>,
) {
    val currentValue: MutableLiveData<Int> = counter.value

    private val incrementHandler: CoroutineHandler = CoroutineHandlerDefault() {
        while (true) {
            delay(1000)
            counter.increment(incrementValue.value!!)
        }
    }

    fun startIncrementing() {
        incrementHandler.start()
    }

    fun stopIncrementing() {
        incrementHandler.cancel()
    }
}