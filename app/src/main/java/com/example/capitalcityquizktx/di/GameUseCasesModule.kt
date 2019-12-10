package com.example.capitalcityquizktx.di

import android.app.Application
import com.example.capitalcityquizktx.domain.GameInteractor
import com.example.capitalcityquizktx.domain.GameUseCases
import com.example.capitalcityquizktx.model.DataRepository
import org.koin.core.module.Module
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
object GameUseCasesModule {
    fun getModules() : Module =  module {
        fun provideGameInteractor(dataRepository: DataRepository, application: Application) : GameUseCases {
            return GameInteractor(dataRepository, application)
        }
        single { provideGameInteractor(get(), get()) }
    }
}