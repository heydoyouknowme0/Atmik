package com.build.atmik.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.sql.Date


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
    @ColumnInfo(index = true)
    val journalId: Long
)
