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
open class CountryRepositoryImpl(private val dataCsvLoader : DataCsvLoader,
                                 private val countryDatabase: CountryDatabaseDao,
                                 private val context : Context
) : CountryRepository {
    override fun removeCountries() {
        countryDatabase.destroyCountries()
    }

    override fun removeCountry(country: Country) {

    }

    override fun getCountriesFromFile(): List<Country> {
        // FIXME This context, not only leakes but WTF!
        return dataCsvLoader.getCountryList(context.resources.openRawResource(R.raw.allcountries),
            ContinentSelector())
    }

    override fun insertCountries(countries: List<Country>) {
        countryDatabase.insertAllCountries(countries)
    }

    override fun getFieldsCount(): Int {
        return countryDatabase.dataFieldsCount()
    }

    override fun getCountryList(): Single<MutableList<Country>> {
        // TODO needs doing
        return Single.just(mutableListOf())
    }

    override fun getCountriesByContinents(continents: List<Continent>): List<Country> {
        val tempList = mutableListOf<Country>()

        // collect all the countries in a temporary list
        for (i in continents.indices){
            tempList.addAll(countryDatabase.getCountriesBy(continents[i].continentName))
        }
        return tempList
    }
}
    