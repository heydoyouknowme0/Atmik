package com.build.atmik.data
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "entries",
    foreignKeys = [
        ForeignKey(
            entity = Journal::class,
            parentColumns = ["id"],
            childColumns = ["journalId"],
            onDelete = CASCADE
        )
    ]
)
data class Entry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val time: String,
    val entryName: String,
    val data: String?,
    val journalId: Long
)
