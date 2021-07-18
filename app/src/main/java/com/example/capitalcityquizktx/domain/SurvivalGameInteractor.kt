package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single
import java.util.*

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
open class SurvivalGameInteractor(
    private val countryRepository: CountryRepository
) : SurvivalGameUseCases {

    override fun removeCountry(country: Country) {
        countryRepository.removeCountry(country)
    }

    override fun getAllCountries(): Single<MutableList<Country>> {
        return countryRepository.getCountryList()
    }

    override fun getCountriesIn(continents: List<Continent>): List<Country> {
        return getCountriesBy(continents).shuffled()
    }

    private fun getCountriesBy(continents: List<Continent>): List<Country> {
        return countryRepository.getCountryListBy(continents)
    }

    override fun destroyCountries() {
        countryRepository.removeCountries()
    }

    override fun insertAllCountries(countries: List<Country>) {
        countryRepository.insertCountries(countries)
    }

    override fun getDataFieldsCount(): Int {
        return countryRepository.getFieldsCount()
    }

    override fun getCountriesFromFile(): List<Country> {
//        DatabaseUtils.getCountriesFromStream(applicationContext
//            .resources.openRawResource(R.raw.allcountries), ContinentSelector() )
        return countryRepository.getCountriesFromFile()
    }

//    override fun getLearnedCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getLearningCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}