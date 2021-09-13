package com.example.databinding.data

import androidx.lifecycle.MutableLiveData


class Counter(initialValue: Int) {
    var value: MutableLiveData<Int> = MutableLiveData(initialValue)
        private set

    fun increment(value: Int) {
        this.value.value?.let {
            this.value.postValue(it + value)
        }
    }
}