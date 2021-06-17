package com.example.capitalcityquizktx.data

import android.content.Context
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import com.example.capitalcityquizktx.common.utils.ContinentSelector
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
open class DataDownloader(private val dataCsvLoader : DataCsvLoader,
                     private val database: CountryDatabaseDao,
                     private val context : Context
) : CountryRepository {
    override fun removeCountries() {
        database.destroyCountries()
    }

    override fun removeCountry(country: Country) {

    }

    override fun getCountriesFromFile(): List<Country> {
        return dataCsvLoader.getCountryList(context.resources.openRawResource(R.raw.allcountries),
            ContinentSelector())
    }

    override fun insertCountries(countries: List<Country>) {
        database.insertAllCountries(countries)
    }

    override fun getFieldsCount(): Int {
        return database.dataFieldsCount()
    }

    override fun getCountryList(): Single<MutableList<Country>> {
        // TODO needs doing
        return Single.just(mutableListOf())
    }

    override fun getCountryListBy(continents: List<Continent>): List<Country> {
        val tempList = mutableListOf<Country>()

        // collect all the countries in a temporary list
        for (i in continents.indices){
            tempList.addAll(database.getCountriesBy(continents[i].continentName))
        }
        return tempList
    }
}
    