package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.domain.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
/*
    Currently unavailable
 */
object HomeModules {

    fun load() = loadModules

    private val loadModules by lazy {
        loadKoinModules(viewModelModules)
    }

    private val viewModelModules = module {
        viewModel { HomeViewModel() }
    }

}