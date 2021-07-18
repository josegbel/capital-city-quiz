package com.example.capitalcityquizktx.domain.viewmodels

import com.example.capitalcityquizktx.CapitalCityQuizApp
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Africa
import com.example.capitalcityquizktx.data.models.geographical.continents.Asia
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import com.example.capitalcityquizktx.di.DatabaseModule
import com.example.capitalcityquizktx.di.SurvivalViewModelModule
import com.example.capitalcityquizktx.testUtil.MainCoroutineRule
import com.example.capitalcityquizktx.testUtil.TestData
import com.example.capitalcityquizktx.ui.survivalmode.SurvivalGameViewModel
import io.reactivex.Single
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.stubbing.Answer

class SurvivalViewModelTest : KoinTest {

    companion object {
        private const val FIELD_COUNT = 10
    }

    private val mockedRepositoryModule by lazy {
        module {
            single { mockedRepository }
        }
    }

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    lateinit var application: CapitalCityQuizApp

    @Mock
    lateinit var mockedRepository: CountryRepository

    private val survivalGameViewModel : SurvivalGameViewModel by inject()
    private lateinit var mocks: AutoCloseable

    @BeforeEach
    fun setUp(){
        mocks = MockitoAnnotations.openMocks(this)

        startKoin {
            androidContext(application)
            modules(listOf(
                SurvivalViewModelModule.getModule(),
                mockedRepositoryModule,
                DatabaseModule.getModule())
            )
        }
    }

    @AfterEach
    fun tearDown(){
        stopKoin()
        mocks.close()
    }


    @Test
    fun `Gets a new question from a list of countries that is not empty`() {
        val countries = TestData.COUNTRIES
        val question = survivalGameViewModel.getNextQuestion(countries)
        Assert.assertEquals(TestData.COUNTRY1, question) // getOrAwaitValue is not working
    }

    @Test
    fun `Gets no question from a list of countries that is empty`() {
        val countries = mutableListOf<Country>()
        val country = survivalGameViewModel.getNextQuestion(countries)
        Assert.assertEquals(null, country)
    }

    @Test
    fun `A correct answer is identical to question answer`() {
        val question = TestData.COUNTRY1
        val answer = "Madrid"
        val result = survivalGameViewModel.checkAnswer(question, answer)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(true))
    }

    @Test
    fun `A correct answer is same to question answer but is not case sensitive`() {
        val question = TestData.COUNTRY1
        val answer = "madrid"
        val result = survivalGameViewModel.checkAnswer(question, answer)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(true))
    }

    @Test
    fun `An incorrect answer is very different to the question answer`() {
        val question = TestData.COUNTRY1
        val answer = "paris"
        val result = survivalGameViewModel.checkAnswer(question, answer)
        MatcherAssert.assertThat(result, CoreMatchers.`is`(false))
    }

    @Test
    @Throws(Exception::class)
    fun `The question is removed from the question list if answer is correct`() {
        val country = TestData.COUNTRY1
        val answer = "madrid"
        val argumentCaptor = argumentCaptor<Country>()
        survivalGameViewModel.checkAnswer(country, answer)
        verify(mockedRepository, Times(1)).removeCountry(argumentCaptor.capture())
        Assert.assertEquals(country, argumentCaptor.firstValue)
    }

    @Test
    fun `Get a list of all the countries from the selected continents`() {
        whenever(mockedRepository.getCountriesByContinents(any())).thenReturn(TestData.COUNTRIES)
        val actual = survivalGameViewModel.getCountriesIn(listOf(Europe, Africa, Asia))
        Assert.assertTrue(
            actual.size == TestData.COUNTRIES.size
                    && actual.containsAll(TestData.COUNTRIES) && TestData.COUNTRIES.containsAll(
                actual
            )
        )
    }

    @Test
    fun `Delete all the countries from the database`() {
        whenever(mockedRepository.removeCountries()).thenAnswer { Answer { } }
        survivalGameViewModel.destroyCountries()
        verify(mockedRepository, Times(1)).removeCountries()
    }

    @Test
    fun `Insert a list of countries to the database`() {
        val countryListCaptor = argumentCaptor<List<Country>>()
        whenever(mockedRepository.insertCountries(any())).thenAnswer { Answer { emptyList<Country>() } }
        survivalGameViewModel.insert(TestData.COUNTRIES)
        verify(mockedRepository, Times(1)).insertCountries(countryListCaptor.capture())
        Assert.assertEquals(countryListCaptor.firstValue, TestData.COUNTRIES)
    }

    @Test
    fun `Retrieves the number of all the elements in the database of countries`() {
        whenever(mockedRepository.getFieldsCount()).thenReturn(FIELD_COUNT)
        val actual = survivalGameViewModel.getDataFieldsCount()
        Assert.assertEquals(FIELD_COUNT, actual)
    }

    @Test
    fun `Transforms a csv file into a list of Country objects`() = coroutineRule.runBlockingTest {
        whenever(mockedRepository.getCountriesFromFile()).thenReturn(TestData.COUNTRIES)
        val actual = survivalGameViewModel.getCountriesFromFile()
        Assert.assertEquals(TestData.COUNTRIES, actual)
    }

    @Test
    fun `Retrieves all countries from the database`() {
        val expected = Single.just(TestData.COUNTRIES)
        whenever(mockedRepository.getCountryList()).thenReturn(expected)
        val actual = survivalGameViewModel.getAllCountries()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Retrieves a certain number of countries from the database as a list`() {
        val expected = Single.just(TestData.COUNTRIES)
        whenever(mockedRepository.getCountryList()).thenReturn(expected)
        val actual = survivalGameViewModel.getAllCountries()
        Assert.assertEquals(expected, actual)
    }
}