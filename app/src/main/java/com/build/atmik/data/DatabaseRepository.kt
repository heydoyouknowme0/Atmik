package com.build.atmik.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseRepository(private val entryDao: EntryDao,private val journalDao: JournalDao) {
    val getAllJournals: LiveData<List<Journal>> =journalDao.getAllJournals()
    fun getEntriesForJournal(journalId: Long): LiveData<List<Entry>> {
        return entryDao.getEntriesForJournal(journalId)
    }
    suspend fun addJournal(journal: Journal){
        journalDao.insert(journal)
    }
    suspend fun addEntry(entry: Entry){
        entryDao.insert(entry)
    }
    suspend fun deletebyIdj(id: Long){
        journalDao.deleteById(id)
    }
    suspend fun update(id: Long,data:String){
        entryDao.updateData(id,data)
    }
    fun getData(id: Long): LiveData<Entry> {
        return entryDao.getEntryInformation(id)
    }

}