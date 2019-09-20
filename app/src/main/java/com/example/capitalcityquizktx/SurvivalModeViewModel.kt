package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class SurvivalModeViewModel(
    val database: CountryDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
}