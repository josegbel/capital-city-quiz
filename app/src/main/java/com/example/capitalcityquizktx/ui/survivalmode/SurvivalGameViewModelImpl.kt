package com.example.capitalcityquizktx.ui.survivalmode

import androidx.lifecycle.*
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.domain.ISurvivalGame
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single
import kotlinx.coroutines.*
import java.util.*

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class SurvivalGameViewModelImpl(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val countryRepository: CountryRepository
) : SurvivalGameViewModel(), ISurvivalGame {
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

    override fun repopulateCountriesInCache() {
        viewModelScope.launch {
            withContext(coroutineDispatcher) {
                destroyCountries()
                insert(getCountriesFromFile())
            }
        }
    }

    override fun getAllCountries(): Single<MutableList<Country>> {
        return countryRepository.getCountryList()
    }

    override fun destroyCountries() {
        countryRepository.removeCountries()
    }

    override fun getDataFieldsCount(): Int {
        return countryRepository.getFieldsCount()
    }

    override suspend fun getCountriesFromFile(): List<Country> {
//        DatabaseUtils.getCountriesFromStream(applicationContext
//            .resources.openRawResource(R.raw.allcountries), ContinentSelector() )
        return countryRepository.getCountriesFromFile()
    }

    override fun getCountriesIn(continents: List<Continent>): List<Country> {
        return countryRepository.getCountriesByContinents(continents)
    }

    override fun insert(countries: List<Country>) {
        countryRepository.insertCountries(countries)
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
