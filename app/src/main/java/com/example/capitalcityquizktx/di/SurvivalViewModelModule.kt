package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.ui.survivalmode.SurvivalGameViewModel
import com.example.capitalcityquizktx.ui.survivalmode.SurvivalGameViewModelImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
object SurvivalViewModelModule {
    fun getModule(): Module = module {
        fun provideSurvivalViewModel(coroutineDispatcher: CoroutineDispatcher,
                                     countryRepository: CountryRepository
        ): SurvivalGameViewModel = SurvivalGameViewModelImpl(coroutineDispatcher, countryRepository)

        factory { Dispatchers.Default }
        viewModel { provideSurvivalViewModel(get(), get()) }
    }
}