package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import com.example.capitalcityquizktx.Utils.ContinentSelector
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import kotlinx.coroutines.*

class SurvivalModeViewModel(
    val database: CountryDatabaseDao,
    application: Application,
    private val testDispatcher: CoroutineDispatcher) : AndroidViewModel(application) {

    val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun populateDatabase() {
        uiScope.launch {
            insertCountries(getListOfCountries())
        }
    }

    private suspend fun insertCountries(countries: List<Country>) {
        withContext(testDispatcher) {
            database.insertAllCountries(countries)
        }
    }
//
//    private suspend fun insertContinents(continents: List<Continent>) {
//        withContext(testDispatcher) {
//            database.insertAllContinents(continents)
//        }
//    }

    private suspend fun getListOfCountries(): List<Country> {
        // Creating the list of countries from a raw file (csv)
        return withContext(testDispatcher){
                DatabaseUtils.getCountriesFromStream(getApplication<Application>()
                    .applicationContext.resources.openRawResource(R.raw.allcountries), ContinentSelector())
        }
    }

    fun shouldPopulate(): Boolean{
        if (database.dataFieldsCount() == 0)
            return true
        else if (database.dataFieldsCount() == 197)
            return false
        return false
    }

}