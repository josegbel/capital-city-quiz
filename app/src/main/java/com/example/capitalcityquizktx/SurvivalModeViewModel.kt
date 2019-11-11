package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import kotlinx.coroutines.*

class SurvivalModeViewModel(
    val database: CountryDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun populateDatabase() {
        uiScope.launch {
            if (database.dataFieldsCount() == 0){
                val countries = getListOfCountries()

                insertCountries(countries)
            }else if (database.dataFieldsCount() > 0) {
                // Do nothing else
                //TODO Probably I should check that the data is correct?
            }else{
                throw Exception("Failed to get data from database")
            }
        }
    }

    private suspend fun insertCountries(countries: List<Country>) {
        withContext(Dispatchers.IO) {
            database.insertAll(countries)
        }
    }

    private suspend fun getListOfCountries(): List<Country> {
        // Creating the list of countries from a raw file (csv)
            return withContext(Dispatchers.IO){
                DatabaseUtils.fromCsvToList(getApplication<Application>()
                    .applicationContext.resources.openRawResource(R.raw.allcountries))

            }

    }

}