package com.example.capitalcityquizktx.ui.survivalmode

import android.app.Application
import androidx.lifecycle.*
import com.example.capitalcityquizktx.domain.GameUseCases
import com.example.capitalcityquizktx.model.DataApi
import com.example.capitalcityquizktx.model.DataDownloader
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import com.example.capitalcityquizktx.utils.ContinentSelector
import com.example.capitalcityquizktx.utils.DatabaseUtils
import io.reactivex.Scheduler
import io.reactivex.Single
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SurvivalModeViewModel(
    private val gameUseCases: GameUseCases,
    val subscribeOnScheduler: Scheduler,
    val observeOnScheduler: Scheduler,
    private val testDispatcher: CoroutineDispatcher) : ViewModel(), CoroutineScope{

    private val _countries = MutableLiveData<List<Country>>()
//    val countries: LiveData<List<Country>> = _countries

    val viewModelJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun populateDatabase() {
        uiScope.launch {
            deleteAllCountries()
            insertCountries(getListOfCountriesFromFile())
        }
    }

    private suspend fun deleteAllCountries() {
        withContext(testDispatcher){
            gameUseCases.destroyCountries()
        }
    }

    private suspend fun insertCountries(countries: List<Country>) {
        withContext(testDispatcher) {
            gameUseCases.insertAllCountries(countries)
        }
    }

    private suspend fun getListOfCountriesFromFile(): List<Country> {
        // Creating the list of countries from a raw file (csv)
        return withContext(testDispatcher){
            gameUseCases.getCountriesFromStream()

        }
    }

    fun shouldPopulate(): Boolean{
        if (gameUseCases.getDataFieldsCount() == 0)
            return true
        else if (gameUseCases.getDataFieldsCount() == 197)
            return false
        return false
    }

}