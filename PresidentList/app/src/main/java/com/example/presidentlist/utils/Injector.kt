package com.example.presidentlist.utils

import com.example.presidentlist.PresidentAdapter
import com.example.presidentlist.PresidentClickListener
import com.example.presidentlist.PresidentDataSource

object Injector {

    fun providePresidentListAdapter(
        dataSource: PresidentDataSource,
        clickListener: PresidentClickListener
    ): PresidentAdapter {
        return PresidentAdapter(dataSource, clickListener)
    }
}