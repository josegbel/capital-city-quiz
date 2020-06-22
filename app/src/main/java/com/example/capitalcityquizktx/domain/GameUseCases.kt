package com.example.capitalcityquizktx.domain

import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface GameUseCases {
//    fun getLearnedCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
//    fun getLearningCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
    fun getAllCountries() : Single<MutableList<Country>>
    fun getCountriesIn(continents: List<Continent>) : List<Country>
    fun destroyCountries()
    fun insertAllCountries(countries: List<Country>)
    fun getDataFieldsCount(): Int
    fun getCountriesFromStream() : List<Country>
    fun getNextQuestion(list: MutableList<Country>) : Country?
    fun checkAnswer(question: Country, answer: String): Boolean
    fun removeCountry(country: Country)
}