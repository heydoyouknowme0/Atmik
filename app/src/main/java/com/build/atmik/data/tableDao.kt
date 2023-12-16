package com.build.atmik.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JournalDao {
    @Insert
    suspend fun insert(journal: Journal)
    @Query("SELECT * FROM journals ORDER BY id ASC")
    fun getAllJournals(): LiveData<List<Journal>>

    @Query("DELETE FROM journals WHERE id = :id")
    suspend fun deleteById(id: Long)
    @Query("UPDATE journals SET name=:data WHERE id= :id")
    suspend fun update(id: Long,data:String)
}

@Dao
interface EntryDao {
    @Insert
    suspend fun insert(entry: Entry)

    @Query("SELECT * FROM entries WHERE id=:id LIMIT 1")
    fun getEntryInformation(id: Long): LiveData<Entry>

    @Query("DELETE FROM entries WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE entries SET data=:data WHERE id= :id")
    suspend fun updateData(id: Long,data:String)
    @Query("UPDATE entries SET entryName=:name WHERE id= :id")
    suspend fun updateName(id: Long,name:String)
    @Query("SELECT id,journalId,time,entryName FROM entries WHERE journalId = :journalId")
    fun getEntriesForJournal(journalId: Long): LiveData<List<Entry>>

    @Query("SELECT id,journalId,time,entryName FROM entries")
    fun getEntries(): LiveData<List<Entry>>
}
