package com.example.capitalcityquizktx.di

import android.content.Context
import com.example.capitalcityquizktx.model.DataApi
import com.example.capitalcityquizktx.model.DataCsvLoader
import com.example.capitalcityquizktx.model.DataDownloader
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao_Impl
import org.koin.core.module.Module
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
object RepositoryModule {
    fun getModule() : Module = module {
        fun provideCountryRepository(dataCsvLoader: DataCsvLoader,
                                     dataApi : DataApi,
                                     dao : CountryDatabaseDao,
                                     context: Context
        ): DataRepository {

            return DataDownloader(dataCsvLoader, dataApi, dao, context)
        }
        single { provideCountryRepository(get(), get(), get(), get()) }
        single { DataCsvLoader() }
        single { DataApi() }
        single { CountryDatabaseDao_Impl(get()) }
    }
}