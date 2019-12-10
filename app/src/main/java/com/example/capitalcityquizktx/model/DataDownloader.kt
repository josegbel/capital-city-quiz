package com.example.capitalcityquizktx.model

import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class DataDownloader(private val dataCsvLoader : DataCsvLoader,
                     private val dataApi: DataApi,
                     private val database: CountryDatabaseDao) : DataRepository {
    override fun getFieldsCount(): Int {
        return database.dataFieldsCount()
    }

    override fun getCountryList(): Single<List<Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountryList(gameConfig: GameConfig): Single<List<Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
    