package com.example.capitalcityquizktx.di

import android.app.Application
import com.example.capitalcityquizktx.domain.GameInteractor
import com.example.capitalcityquizktx.domain.GameUseCases
import com.example.capitalcityquizktx.model.DataApi
import com.example.capitalcityquizktx.model.DataDownloader
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.ui.survivalmode.SurvivalViewModel
import kotlinx.coroutines.Dispatchers

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val survivalViewModelModule = module{
    single { Dispatchers.Default  }
    viewModel { SurvivalViewModel( get(), get() )}
}

val useCaseModule = module {
    //factory { Application() }
    fun provideGameInteractor(dataRepository: DataRepository, application: Application) : GameUseCases{
        return GameInteractor(dataRepository, application)
    }
    single { provideGameInteractor(get(), get()) }
}

val repositoryModule = module {
    fun provideCountryRepository(api: DataApi) : DataRepository {
        return DataDownloader(api)
    }
    single { provideCountryRepository(get())}
    single { DataApi() }
}

//val appModule = module {
//    //factory { Application() }
//    factory { GameInteractor(get(), get()) ; Dispatchers.Default }
//    viewModel { SurvivalViewModel(get() as GameUseCases, Dispatchers.Default ) }
//}
