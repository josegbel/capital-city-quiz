package com.example.capitalcityquizktx.data

import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface CountryRepository {
    fun getCountryList() : Single<List<Country>>
    fun getCountryListBy(continents: List<Continent>) : List<Country>
    fun getFieldsCount() : Int
    fun insertCountries(countries: List<Country>)
    fun getCountriesFromFile(): List<Country>
    fun removeCountries()
}
