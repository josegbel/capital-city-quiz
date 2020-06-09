package com.example.capitalcityquizktx.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import com.example.capitalcityquizktx.utils.ContinentSelector
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class DataDownloader(private val dataCsvLoader : DataCsvLoader,
                     private val dataApi: DataApi,
                     private val database: CountryDatabaseDao,
                     private val context : Context
) : DataRepository {
    override fun removeCountries() {
        database.destroyCountries()
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

    override fun getCountryList(): Single<List<Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountryListBy(continents: List<Continent>): List<Country> {
//        val countries = MutableLiveData<MutableList<Country>>()
        val tempList = mutableListOf<Country>()

        // collect all the countries in a temporary list
        for (i in continents.indices){
            tempList.addAll(database.getCountriesBy(continents[i].continentName))
        }
//
//        // post the countries to a liveData
//        countries.postValue(tempList)
        return tempList
    }
}
    