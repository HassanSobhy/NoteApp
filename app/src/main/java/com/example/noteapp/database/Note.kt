package com.example.noteapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,
    @ColumnInfo(name = "title")
    var title: String = "title",
    @ColumnInfo(name = "description")
    val desc: String = "description",
    @ColumnInfo(name = "priority")
    val priority: Int = -1
)