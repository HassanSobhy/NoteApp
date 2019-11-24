package com.example.noteapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapp.database.NoteDatabaseDao
import kotlinx.coroutines.*

class HomeViewModel(private val database: NoteDatabaseDao) : ViewModel() {


    /**
     * viewModelJob allows us to cancel all coroutines started by this ViewModel.
     */
    val viewModelJob = Job()

    /**
     * A [CoroutineScope] keeps track of all coroutines started by this ViewModel.
     *
     * Because we pass it [viewModelJob], any coroutine started in this uiScope can be cancelled
     * by calling `viewModelJob.cancel()`
     *
     * By default, all coroutines started in uiScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val notes = database.getAllNotes()


    private var _navigateToAddNoteFragment = MutableLiveData<Boolean>()

    val navigateToAddNoteFragment: LiveData<Boolean>
        get() = _navigateToAddNoteFragment


    fun doneNavigating() {
        _navigateToAddNoteFragment.value = false
    }

    fun onAddNote() {
        _navigateToAddNoteFragment.value = true
    }


    /**
     * Request a toast by setting this value to true.
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    /**
     * If this is true, immediately `show()` a toast and call `doneShowingSnackbar()`.
     */
    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onClear()
    {
        uiScope.launch {
            clear()
        }

        _showSnackbarEvent.value = true
    }

    private suspend fun clear()
    {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}