package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import android.app.Activity
import com.example.capitalcityquizktx.di.DatabaseModule
import com.example.capitalcityquizktx.di.GameUseCasesModule
import com.example.capitalcityquizktx.di.RepositoryModule
import com.example.capitalcityquizktx.di.SurvivalViewModelModule
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import com.example.capitalcityquizktx.ui.survivalmode.SurvivalViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock

class SurvivalViewModelTest : KoinTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var activity: Activity

    @RelaxedMockK
    lateinit var dataSource: CountryDatabaseDao

    private val survivalViewModel : SurvivalViewModel by inject()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)

        val application = requireNotNull(activity).application

        startKoin {
            androidContext(application)
            modules(listOf(
                SurvivalViewModelModule.getModule(),
                RepositoryModule.getModule(),
                GameUseCasesModule.getModules(),
                DatabaseModule.getModule()))
        }

        declareMock<CountryDatabaseDao>()

    }

    @After
    fun tearDown(){
        stopKoin()
    }

    @Test
    fun `given a liveData that emmits when calling getCountries then shuffles the data` (){
        val data = dataSource.getCountries()
    }
}