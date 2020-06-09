package com.example.capitalcityquizktx.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface DataRepository {
    fun getCountryList() : Single<List<Country>>
    fun getCountryListBy(continents: List<Continent>) : List<Country>
    fun getFieldsCount() : Int
    fun insertCountries(countries: List<Country>)
    fun getCountriesFromFile(): List<Country>
    fun removeCountries()
}
