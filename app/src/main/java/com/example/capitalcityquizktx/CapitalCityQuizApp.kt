package com.example.capitalcityquizktx

import android.app.Application
import com.example.capitalcityquizktx.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class CapitalCityQuizApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CapitalCityQuizApp)
            modules(listOf(
                GameUseCasesModule.getModules(),
                RepositoryModule.getModule(),
                SurvivalViewModelModule.getModule(),
                DatabaseModule.getModule()))
        }
    }
}

