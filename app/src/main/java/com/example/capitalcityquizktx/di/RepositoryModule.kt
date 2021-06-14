package com.example.capitalcityquizktx.di

import android.content.Context
import com.example.capitalcityquizktx.data.DataCsvLoader
import com.example.capitalcityquizktx.data.DataDownloader
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.local.CountryDatabase
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import org.koin.core.module.Module
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
object RepositoryModule {
    fun getModule() : Module = module {
        fun provideCountryRepository(dataCsvLoader: DataCsvLoader,
                                     dao : CountryDatabaseDao,
                                     context: Context
        ): CountryRepository {

            return DataDownloader(dataCsvLoader, dao, context)
        }
        single { provideCountryRepository(get(), get(), get()) }
        factory { DataCsvLoader() }
//        factory { CountryDatabase(get()) }
    }
}