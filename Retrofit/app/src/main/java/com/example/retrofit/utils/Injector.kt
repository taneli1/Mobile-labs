package com.example.retrofit.utils

import com.example.retrofit.PresidentAdapter
import com.example.retrofit.PresidentClickListener
import com.example.retrofit.PresidentDataSource
import com.example.retrofit.data.repo.WikiRepository
import com.example.retrofit.ui.viewmodel.PresidentViewModel

object Injector {

    // Keep a single repo instance
    private val wikiRepository = WikiRepository()

    fun providePresidentListAdapter(
        dataSource: PresidentDataSource,
        clickListener: PresidentClickListener
    ): PresidentAdapter {
        return PresidentAdapter(dataSource, clickListener)
    }

    fun providePresidentViewModelFactory(): PresidentViewModel.Factory {
        return PresidentViewModel.Factory(wikiRepository)
    }
}