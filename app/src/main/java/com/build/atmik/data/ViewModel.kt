package com.build.atmik.data
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val getAllJournals: LiveData<List<Journal>>
    private val repository: DatabaseRepository

    init {
        val journalDao = AppDatabase.getDatabase(application).journalDao()
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository= DatabaseRepository(entryDao,journalDao)
        getAllJournals=repository.getAllJournals
    }
    fun addJournal(journal: Journal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addJournal(journal)
        }
    }


}

