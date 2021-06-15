package com.example.capitalcityquizktx.domain

import android.app.Application
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Africa
import com.example.capitalcityquizktx.data.models.geographical.continents.Asia
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import com.example.capitalcityquizktx.di.GameUseCasesModule
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.internal.verification.Times
import org.mockito.stubbing.Answer
import com.example.capitalcityquizktx.testUtil.TestData
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.inject
import org.mockito.*
import org.mockito.kotlin.*

class GameInteractorTest : KoinTest {

    companion object {
        private const val FIELD_COUNT = 10
    }

    private val interactor: GameInteractor by inject()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repository: CountryRepository

    @Mock
    lateinit var countryDatabaseDaoMock: CountryDatabaseDao

    private lateinit var countryListCaptor: KArgumentCaptor<List<Country>>

    private lateinit var mocks: AutoCloseable

    private val mockedDbModule by lazy {
        module {
            single { countryDatabaseDaoMock }
        }
    }
    private val mockedRepositoryModule by lazy {
        module {
            single { repository }
        }
    }

    @BeforeEach
    fun setUp() {
        mocks = MockitoAnnotations.openMocks(this)
        countryListCaptor = argumentCaptor()

        startKoin {
            androidContext(application)
            modules(
                listOf(
                    mockedRepositoryModule,
                    mockedDbModule,
                    GameUseCasesModule.getModules()
                )
            )
        }
    }

    @AfterEach
    fun tearDown() {
        stopKoin()
        mocks.close()
    }

    @Test
    fun getNextQuestion_listOfCountries_returnsNextQuestion() {
        val countries = TestData.COUNTRIES
        val question = interactor.getNextQuestion(countries)
        assertEquals(TestData.COUNTRY1, question) // getOrAwaitValue is not working
    }

    @Test
    fun getNextQuestion_emptyList_returnsNull() {
        val countries = mutableListOf<Country>()
        val country = interactor.getNextQuestion(countries)
        assertEquals(null, country)
    }

    @Test
    fun checkAnswer_correctAnswer_trueReturned() {
        val question = TestData.COUNTRY1
        val answer = "Madrid"
        val result = interactor.checkAnswer(question, answer)
        assertThat(result, `is`(true))
    }

    @Test
    fun checkAnswer_correctAnswerCaseSensitive_trueReturned() {
        val question = TestData.COUNTRY1
        val answer = "madrid"
        val result = interactor.checkAnswer(question, answer)
        assertThat(result, `is`(true))
    }

    @Test
    fun checkAnswer_wrongAnswer_falseReturned() {
        val question = TestData.COUNTRY1
        val answer = "paris"
        val result = interactor.checkAnswer(question, answer)
        assertThat(result, `is`(false))
    }

    @Test
    @Throws(Exception::class)
    fun removeCountry_success_repositoryIsCalledWithCorrectData() {
        val country = TestData.COUNTRY1
        val argumentCaptor = argumentCaptor<Country>()
        interactor.removeCountry(country)
        verify(repository, Times(1)).removeCountry(argumentCaptor.capture())
        assertEquals(country, argumentCaptor.firstValue)
    }

    @Test
    fun getCountriesIn_listOfContinents_returnsCountryList() {
        whenever(repository.getCountryListBy(any())).thenReturn(TestData.COUNTRIES)
        val actual = interactor.getCountriesIn(listOf(Europe, Africa, Asia))
        assertTrue(
            actual.size == TestData.COUNTRIES.size
                    && actual.containsAll(TestData.COUNTRIES) && TestData.COUNTRIES.containsAll(
                actual
            )
        )
    }

    @Test
    fun destroyCountries_callsMethodInRepository() {
        whenever(repository.removeCountries()).thenAnswer { Answer { } }
        interactor.destroyCountries()
        verify(repository, Times(1)).removeCountries()
    }

    @Test
    fun insertAllCountries_callsRepositoryWithRightParams() {
        whenever(repository.insertCountries(any())).thenAnswer { Answer { emptyList<Country>() } }
        interactor.insertAllCountries(TestData.COUNTRIES)
        verify(repository, Times(1)).insertCountries(countryListCaptor.capture())
        assertEquals(countryListCaptor.firstValue, TestData.COUNTRIES)
    }

    @Test
    fun getDataFieldCount_returnsDataFieldCount() {
        whenever(repository.getFieldsCount()).thenReturn(FIELD_COUNT)
        val actual = interactor.getDataFieldsCount()
        assertEquals(FIELD_COUNT, actual)
    }

    @Test
    fun getCountriesFromStream_returnsCountryList() {
        whenever(repository.getCountriesFromFile()).thenReturn(TestData.COUNTRIES)
        val actual = interactor.getCountriesFromStream()
        assertEquals(TestData.COUNTRIES, actual)
    }

    @Test
    fun getAllCountries_returnsSingleCountryList() {
        val expected = Single.just(TestData.COUNTRIES)
        whenever(repository.getCountryList()).thenReturn(expected)
        val actual = interactor.getAllCountries()
        assertEquals(expected, actual)
    }
}
