package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.domain.SurvivalGameInteractor
import com.example.capitalcityquizktx.domain.SurvivalGameUseCases
import org.koin.core.module.Module
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
object SurvivalGameUseCasesModule {
    fun getModules() : Module =  module {
        single { provideSurvivalGameUseCases(get()) }
    }
    private fun provideSurvivalGameUseCases(countryRepository: CountryRepository)
    : SurvivalGameUseCases = SurvivalGameInteractor(countryRepository)
}