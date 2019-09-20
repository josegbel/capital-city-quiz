package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capitalcityquizktx.Database.CountryDatabaseDao

class SurvivalModeViewModelFactory (
    private val dataSource: CountryDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SurvivalModeViewModel::class.java)){
            return SurvivalModeViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}