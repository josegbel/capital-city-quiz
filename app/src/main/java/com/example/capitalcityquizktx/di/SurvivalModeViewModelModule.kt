package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.domain.GameInteractor
import com.example.capitalcityquizktx.model.DataDownloader
import com.example.capitalcityquizktx.model.DataRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object SurvivalModeViewModelModule {
    fun getModule() : Module = module {
        single { GameInteractor(get()) }
    }
}