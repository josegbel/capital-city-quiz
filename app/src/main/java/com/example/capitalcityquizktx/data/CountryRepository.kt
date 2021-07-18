package com.example.capitalcityquizktx.data

import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single
import java.io.InputStream

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface CountryRepository {
    fun getCountryList() : Single<MutableList<Country>>
    fun getCountriesByContinents(continents: List<Continent>) : List<Country>
    fun getFieldsCount() : Int
    fun insertCountries(countries: List<Country>)
    fun getCountriesFromCsvFile(inputStream: InputStream): List<Country>
    fun removeCountries()
    fun removeCountry(country: Country)
}
