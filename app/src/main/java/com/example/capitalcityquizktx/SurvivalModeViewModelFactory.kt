package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import kotlinx.coroutines.Dispatchers

class SurvivalModeViewModelFactory (
    private val dataSource: CountryDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SurvivalModeViewModel::class.java)){
            return SurvivalModeViewModel(dataSource, application, Dispatchers.Default) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}