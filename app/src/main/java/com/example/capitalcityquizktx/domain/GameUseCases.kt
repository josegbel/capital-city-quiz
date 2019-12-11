package com.example.capitalcityquizktx.domain

import androidx.lifecycle.LiveData
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface GameUseCases {
//    fun getLearnedCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
//    fun getLearningCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
    fun getAllCountries() : Single<List<Country>>
    fun getCountriesIn(continent: Continent) : LiveData<MutableList<Country>>
    fun destroyCountries()
    fun insertAllCountries(countries: List<Country>)
    fun getDataFieldsCount(): Int
    fun getCountriesFromStream() : List<Country>
}