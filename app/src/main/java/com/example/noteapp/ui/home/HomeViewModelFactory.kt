package com.example.noteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.database.NoteDatabaseDao


/**
 * This is pretty much boiler plate code for a ViewModel Factory.
 *
 * Provides the NoteDatabaseDao to the ViewModel.
 */
class HomeViewModelFactory (private val dataSource: NoteDatabaseDao) :ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}