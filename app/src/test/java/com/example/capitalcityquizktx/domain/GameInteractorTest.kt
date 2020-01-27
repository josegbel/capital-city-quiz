package com.example.capitalcityquizktx.domain

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import assertk.assertThat
import com.example.capitalcityquizktx.di.DatabaseModule
import com.example.capitalcityquizktx.di.GameUseCasesModule
import com.example.capitalcityquizktx.di.RepositoryModule
import com.example.capitalcityquizktx.di.SurvivalViewModelModule
import com.example.capitalcityquizktx.model.database.CapitalCity
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.continents.*
import io.mockk.Called
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

    @Test
    fun given_list_getNextQuestion_should_return_question() {
        val country1 = Country("Spain", CapitalCity("Madrid"), Europe)
        val country2 = Country("Senegal", CapitalCity("Dakar"), Africa)
        val country3 = Country("China", CapitalCity("Beijin"), Asia)
        val country4 = Country("Australia", CapitalCity("Sidney"), Australia)
        val country5 = Country("USA", CapitalCity("Washington"), NorthAmerica)
        val country6 = Country("Peru", CapitalCity("Lima"), SouthAmerica)
        val list = mutableListOf(country1, country2, country3, country4, country5, country6)
        val expectedSizeList = list.size - 1


        val countries = mutableListOf<Country>()
        countries.addAll(list)

        val question = interactor.getNextQuestion(countries)

        // assert that the first element of the list is return
        assertEquals("Spain", question?.value?.countryName)
        // assert that the element is removed from the list
        assertEquals("Senegal", countries[0].countryName)
        assertEquals(expectedSizeList, countries.size)
    }

    @Test
    fun given_empty_list_when_asked_for_next_question_should_implement_game_is_won(){
        val countries = mutableListOf<Country>()

        val country = interactor.getNextQuestion(countries)

        assertEquals(null, country)
    }

    // Todo dont think I should worry about testing the shuffle function
//    @Test
//    fun given_list_shuffleList_should_shuffle_list() {
//        val country1 = Country("Spain", CapitalCity("Madrid"), Europe)
//        val country2 = Country("Senegal", CapitalCity("Dakar"), Africa)
//        val country3 = Country("China", CapitalCity("Beijin"), Asia)
//        val country4 = Country("Australia", CapitalCity("Sidney"), Australia)
//        val country5 = Country("USA", CapitalCity("Washington"), NorthAmerica)
//        val country6 = Country("Peru", CapitalCity("Lima"), SouthAmerica)
//        val list = mutableListOf(country1, country2, country3, country4, country5, country6)
//        val countries = MutableLiveData<MutableList<Country>>()
//        countries.postValue(list)
//
//        assertEquals(list, countries.value)
//
//        val expectedListSize = list.size
//        interactor.shuffleList(countries)
//
//        assertEquals(expectedListSize, countries.value!!.size)
//        assertNotSame(list, countries.value)
//    }

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