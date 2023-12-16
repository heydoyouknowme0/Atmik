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
    val getAllJournals: LiveData<List<Journal>>
    private val repository: DatabaseRepository

    init {
        val journalDao = AppDatabase.getDatabase(application).journalDao()
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository= DatabaseRepository(entryDao,journalDao)
        getAllJournals=repository.getAllJournals
    }
    fun getEntriesForJournal(journalId: Long): LiveData<List<Entry>> {
        return repository.getEntriesForJournal(journalId)
    }
    fun getData(id: Long):LiveData<Entry>{
        return repository.getData(id)
    }
    fun addJournal(journal: Journal){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addJournal(journal)
        }
    }

    fun addEntry(entry: Entry){
        viewModelScope.launch(Dispatchers.IO){
            repository.addEntry(entry)
        }
    }
    fun deleteByIdj(id: Long){
        viewModelScope.launch(Dispatchers.IO){
            repository.deletebyIdj(id)
        }
    }
    fun updateEntry(id: Long,data: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(id,data)
        }
    }

}

