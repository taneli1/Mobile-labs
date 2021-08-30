package com.example.retrofit.ui.viewmodel

import androidx.lifecycle.*
import com.example.retrofit.utils.GlobalModel
import com.example.retrofit.PresidentDataSource
import com.example.retrofit.data.model.President
import com.example.retrofit.data.repo.WikiRepository
import kotlinx.coroutines.Dispatchers

class PresidentViewModel(
    repo: WikiRepository
) : ViewModel(), PresidentDataSource {
    private val fakeDataSet = GlobalModel.presidents

    val selectedPresident = MutableLiveData<President>()

    val queryResponse = selectedPresident.switchMap {
        liveData {
            val data = repo.hitCount(it!!.name)
            emit(data)
        }
    }

    // ... Methods ...

    fun selectPresident(pos: Int) {
        selectedPresident.value = fakeDataSet[pos]
    }

    // ... Other ...

    override fun getDataSetSize(): Int {
        return this.fakeDataSet.size
    }

    override fun getPresident(index: Int): President {
        return this.fakeDataSet[index]
    }

    class Factory(private val repo: WikiRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(WikiRepository::class.java).newInstance(repo)
        }
    }
}