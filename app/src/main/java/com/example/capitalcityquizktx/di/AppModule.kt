package com.example.capitalcityquizktx.di

import com.example.capitalcityquizktx.ui.survivalmode.SurvivalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SurvivalViewModel(get(),get(),get(),get()) }
}