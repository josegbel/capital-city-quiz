package com.example.capitalcityquizktx.ui.survivalmode

import androidx.lifecycle.ViewModel
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single

abstract class SurvivalGameViewModel : ViewModel() {
    abstract fun getNextQuestion(list: MutableList<Country>) : Country?
    abstract fun checkAnswer(question: Country, answer: String): Boolean
    abstract fun insert(countries: List<Country>)
    abstract fun getCountriesIn(continents: List<Continent>) : List<Country>
    abstract fun destroyCountries()
    abstract fun getDataFieldsCount(): Int
    abstract suspend fun getCountriesFromFile() : List<Country>
    abstract fun getAllCountries() : Single<MutableList<Country>>
    abstract fun repopulateCountriesInCache()
}