package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Africa
import com.example.capitalcityquizktx.data.models.geographical.continents.Asia
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.internal.verification.Times
import org.mockito.stubbing.Answer
import testUtil.TestData

class GameInteractorTest : KoinTest {

    private val FIELDSCOUNT: Int = 10
    private lateinit var interactor : GameInteractor

    // Read comment below to understand why this object is not being injected
    //    private val interactor : GameInteractor by inject()
    // Also this activity is not needed
    //    @RelaxedMockK
    //    private lateinit var activity: Activity

    @Mock
    private lateinit var repository: CountryRepository

    @Captor
    private lateinit var countryListCaptor: ArgumentCaptor<List<Country>>

    private lateinit var mocks: AutoCloseable

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        interactor = GameInteractor(repository)
        /** This code is commented out due to an issue with the argument captor not being initialised
         * my intuition is with the fact that I am injecting the dependencies in the wrong way.
         * As it can be seen, I am currently initialising the "interactor" manually and the
         * argument captor is working ... I tried to use Mockito but I get an issue with
         * the "application" being null, which makes me think there is an issue with the way
         * I am using Koin Dependency Injection                                                   */
//        val application = activity.application
//        startKoin {
//            androidContext(application)
//            modules(listOf(
//                DatabaseModule.getModule(),
//                RepositoryModule.getModule(),
//                SurvivalViewModelModule.getModule(),
//                GameUseCasesModule.getModules()))
//        }
    }

    @After
    fun tearDown(){
//        stopKoin()
        mocks.close()
    }

    @Test
    fun getNextQuestion_listOfCountries_returnsNextQuestion() {
        val countries = TestData.COUNTRIES
        val question = interactor.getNextQuestion(countries)
        assertEquals(TestData.COUNTRY1, question) // getOrAwaitValue is not working
    }

    @Test
    fun getNextQuestion_emptyList_returnsNull(){
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
        val argumentCaptor = ArgumentCaptor.forClass(Country::class.java)
        doNothing().`when`(repository.removeCountry(argumentCaptor.capture()))
        interactor.removeCountry(country)
        assertEquals(country, argumentCaptor.value)
    }

    @Test
    fun getCountriesIn_listOfContinents_returnsCountryList() {
        `when`(repository.getCountryListBy(any())).thenReturn(TestData.COUNTRIES)
        val actual = interactor.getCountriesIn(listOf(Europe, Africa, Asia))
        assertTrue(actual.size == TestData.COUNTRIES.size
                && actual.containsAll(TestData.COUNTRIES) && TestData.COUNTRIES.containsAll(actual))
    }

    @Test
    fun destroyCountries_callsMethodInRepository() {
        `when`(repository.removeCountries()).thenAnswer { Answer { } }
        interactor.destroyCountries()
        verify(repository.removeCountries(), Times(1))
    }

    @Test
    fun insertAllCountries_callsRepositoryWithRightParams() {
        `when`(repository.insertCountries(countryListCaptor.capture())).thenAnswer { Answer { } }
        interactor.insertAllCountries(TestData.COUNTRIES)
        assertEquals(countryListCaptor.value, TestData.COUNTRIES)
    }

    @Test
    fun getDataFieldCount_returnsDataFieldCount() {
        `when`(repository.getFieldsCount()).thenReturn(FIELDSCOUNT)
        val actual = interactor.getDataFieldsCount()
        assertEquals(FIELDSCOUNT, actual)
    }

    @Test
    fun getCountriesFromStream_returnsCountryList() {
        `when`(repository.getCountriesFromFile()).thenReturn(TestData.COUNTRIES)
        val actual = interactor.getCountriesFromStream()
        assertEquals(TestData.COUNTRIES, actual)
    }

    @Test
    fun getAllCountries_returnsSingleCountryList() {
        val expected = Single.just(TestData.COUNTRIES)
        `when`(repository.getCountryList()).thenReturn(expected)
        val actual = interactor.getAllCountries()
        assertEquals(expected, actual)
    }
}