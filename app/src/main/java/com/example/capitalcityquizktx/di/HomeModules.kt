package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object HomeModules {

    fun load() = loadModules

    private val loadModules by lazy {
        loadKoinModules(viewModelModules)
    }

    private val viewModelModules = module {
        viewModel { HomeViewModel() }
    }

}