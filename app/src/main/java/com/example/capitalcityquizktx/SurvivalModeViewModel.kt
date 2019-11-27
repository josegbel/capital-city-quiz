package com.example.capitalcityquizktx

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import com.example.capitalcityquizktx.Utils.ContinentSelector
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SurvivalModeViewModel(
    val database: CountryDatabaseDao,
    application: Application,
    private val testDispatcher: CoroutineDispatcher) : AndroidViewModel(application), CoroutineScope{

    private lateinit var isLoading: MutableLiveData<Boolean>
    private lateinit var showError: MutableLiveData<Boolean>
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    val viewModelJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    open val countries: LiveData<List<Country>> = Transformations.map(repository.countries) {countries ->
        if (countries.isNullOrEmpty()){
            loadCountries()
        } else {
            isLoading.value = false
        }
        return@map countries
    }

    open fun getIsLoading(): LiveData<Boolean>{
        if(!::isLoading.isInitialized){
            isLoading = MutableLiveData()
            isLoading.value = true
        }
        return isLoading
    }

    open fun shouldShowError(): LiveData<Boolean>{
        if(!::showError.isInitialized){
            showError = MutableLiveData()
            showError.value = false
        }
        return showError
    }

    open fun loadCountries (){
        launch{
            try{
                isLoading.value = true
                repository.refreshCountries()
            } catch (e: Exception) {
                showError.value = true
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun populateDatabase() {
        uiScope.launch {
            deleteAllCountries()
            insertCountries(getListOfCountriesFromFile())
        }
    }

    private suspend fun deleteAllCountries() {
        withContext(testDispatcher){
            database.destroyCountries()
        }
    }

    private suspend fun insertCountries(countries: List<Country>) {
        withContext(testDispatcher) {
            database.insertAllCountries(countries)
        }
    }

    private suspend fun getListOfCountriesFromFile(): List<Country> {
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