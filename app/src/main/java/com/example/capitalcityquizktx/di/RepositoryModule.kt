package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.model.DataApi
import com.example.capitalcityquizktx.model.DataDownloader
import com.example.capitalcityquizktx.model.DataRepository
import org.koin.dsl.module

object RepositoryModule {
    fun getModule() = module {
        single<DataRepository> { DataDownloader(get()) as DataRepository }

        factory { DataApi() }
    }

}