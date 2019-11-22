package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capitalcityquizktx.Database.Continent
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

                insertContinents(listOf(
                    Continent("Asia", 44),
                    Continent("Europe",50),
                    Continent("Australia", 14),
                    Continent("SouthAmerica", 28),
                    Continent("NorthAmerica", 7),
                    Continent("Africa", 54)
                ))
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
            database.insertAllCountries(countries)
        }
    }

    private suspend fun insertContinents(continents: List<Continent>) {
        withContext(Dispatchers.IO) {
            database.insertAllContinents(continents)
        }
    }

    //THIS IS RETURNING EMPTY LIST!!!!
    private suspend fun getListOfCountries(): List<Country> {
        // Creating the list of countries from a raw file (csv)
//            return withContext(Dispatchers.IO){
//                DatabaseUtils.fromCsvToList(getApplication<Application>()
//                    .applicationContext.resources.openRawResource(R.raw.allcountries))
//            }
        return emptyList()
    }

}