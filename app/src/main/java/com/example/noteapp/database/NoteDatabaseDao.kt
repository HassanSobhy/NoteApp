package com.example.noteapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note: Note)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param note new value to write
     */
    @Update
    fun update(note: Note)

    @Query("DELETE FROM notes_table")
    fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     */
    @Query("SELECT * FROM notes_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by priority
     */

    @Query("SELECT * FROM notes_table ORDER BY priority ASC")
    fun getAllNotesOrderdByPriority(): LiveData<List<Note>>

}