package com.example.presidentlist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.presidentlist.utils.GlobalModel
import com.example.presidentlist.PresidentDataSource
import com.example.presidentlist.model.President

class PresidentViewModel : ViewModel(), PresidentDataSource {
    private val fakeDataSet = GlobalModel.presidents

    override fun getDataSetSize(): Int {
        return this.fakeDataSet.size
    }

    override fun getPresident(index: Int): President {
        return this.fakeDataSet[index]
    }
}