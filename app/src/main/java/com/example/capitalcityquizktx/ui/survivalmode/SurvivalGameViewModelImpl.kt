package com.example.capitalcityquizktx.ui.survivalmode

import androidx.lifecycle.*
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.domain.SurvivalGameUseCases
import com.example.capitalcityquizktx.domain.ISurvivalGame
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class SurvivalGameViewModelImpl(
    val survivalGameUseCases: SurvivalGameUseCases,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val countryRepository: CountryRepository
) : SurvivalGameViewModel(), CoroutineScope, ISurvivalGame {
    override fun gameWon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun gameOver() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val list: MutableLiveData<MutableList<Country>> by lazy {
        MutableLiveData<MutableList<Country>>()
    }

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>>
        get() = _countries

    private val viewModelJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    fun repopulateCountriesInCache() {
        viewModelScope.launch {
            deleteAllCountries()
            insertCountries(getListOfCountriesFromFile())
        }
    }

    fun getCountriesFrom(continents: List<Continent>) {
        viewModelScope.launch(coroutineDispatcher) {
            _countries.postValue(survivalGameUseCases.getCountriesIn(continents))
        }
    }

    private suspend fun deleteAllCountries() {
        withContext(coroutineDispatcher) {
            survivalGameUseCases.destroyCountries()
        }
    }

    private suspend fun insertCountries(countries: List<Country>) {
        withContext(coroutineDispatcher) {
            survivalGameUseCases.insertAllCountries(countries)
        }
    }

    private suspend fun getListOfCountriesFromFile(): List<Country> {
        // Creating the list of countries from a raw file (csv)
        return withContext(coroutineDispatcher) {
            survivalGameUseCases.getCountriesFromFile()
        }
    }








    override fun getNextQuestion(list: MutableList<Country>): Country? {
        if (list.isEmpty()) {
            return null
        }
        return list[0]
    }

    override fun checkAnswer(question: Country, answer: String): Boolean {
        if (question.capitalCity.name.lowercase(Locale.getDefault()) == answer.lowercase(Locale.getDefault())) {
            countryRepository.removeCountry(question)
            return true
        }
        return false
    }
}
