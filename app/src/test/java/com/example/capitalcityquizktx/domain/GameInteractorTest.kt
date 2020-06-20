package com.example.capitalcityquizktx.domain

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.capitalcityquizktx.common.di.DatabaseModule
import com.example.capitalcityquizktx.common.di.GameUseCasesModule
import com.example.capitalcityquizktx.common.di.RepositoryModule
import com.example.capitalcityquizktx.common.di.SurvivalViewModelModule
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito
import testUtil.TestData
import testUtil.getOrAwaitValue

class GameInteractorTest : KoinTest {

    private val interactor : GameInteractor by inject()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var activity: Activity

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        val application = activity.application

        startKoin {
            androidContext(application)
            modules(listOf(
                SurvivalViewModelModule.getModule(),
                RepositoryModule.getModule(),
                GameUseCasesModule.getModules(),
                DatabaseModule.getModule()))
        }
    }

    @After
    fun tearDown(){
        stopKoin()
    }
                // TODO flaky tests due to list mutability ! Find a solution to this problem
    @Test
    fun getNextQuestion_listOfCountries_returnsNextQuestion() {
        val countries = TestData.COUNTRIES
        val question = interactor.getNextQuestion(countries)
        assertEquals(TestData.COUNTRY1, question?.getOrAwaitValue()) // getOrAwaitValue is not working
    }

    @Test
    fun getNextQuestion_listOfCountries_removesQuestionReturnedFromList() {
        val countries = TestData.COUNTRIES
        val expectedSizeList = countries.size - 1
        interactor.getNextQuestion(countries)
        assertEquals(expectedSizeList, countries.size)
    }

    @Test
    fun getNextQuestion_listOfCountries_secondListElementBecomesFirstElement() {
        val countries = TestData.COUNTRIES
        val secondCountry = TestData.COUNTRIES[1]
        interactor.getNextQuestion(countries)
        assertEquals(secondCountry.countryName, countries[0].countryName)
    }

    @Test
    fun getNextQuestion_emptyList_returnsNull(){
        val countries = mutableListOf<Country>()
        val country = interactor.getNextQuestion(countries)
        assertEquals(null, country)
    }

    @Test
    fun getAllCountries() {
    }

    @Test
    fun destroyCountries() {
    }

    @Test
    fun insertAllCountries() {
    }

    @Test
    fun getDataFieldsCount() {
    }

    @Test
    fun getCountriesFromStream() {
    }
}