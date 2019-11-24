package com.example.noteapp.ui.addnote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.database.Note
import com.example.noteapp.database.NoteDatabaseDao
import kotlinx.coroutines.*

class AddNoteViewModel(private val database: NoteDatabaseDao) : ViewModel() {

    val viewModelJob = Job()

    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _navigateToHomeFragment = MutableLiveData<Boolean>()

    val navigateToHomeFragment: LiveData<Boolean>
        get() = _navigateToHomeFragment


    fun doneNavigating() {
        _navigateToHomeFragment.value = false
    }

    fun onSaveClicked(title:String,desc:String) {
        uiScope.launch {
            val note = Note(title = title,desc = desc)
            insert(note)
            _navigateToHomeFragment.value=true
        }
    }

    private suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            database.insert(note)
        }
    }



}