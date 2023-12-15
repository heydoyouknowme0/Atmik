package com.build.atmik.data

import androidx.lifecycle.LiveData

class DatabaseRepository(private val entryDao: EntryDao,private val journalDao: JournalDao) {
    val getAllJournals: LiveData<List<Journal>> =journalDao.getAllJournals()

    suspend fun addJournal(journal: Journal){
        journalDao.insert(journal)
    }

}