package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.ui.survivalmode.SurvivalModeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SurvivalModeViewModel(get(),get(),get(),get()) }
}