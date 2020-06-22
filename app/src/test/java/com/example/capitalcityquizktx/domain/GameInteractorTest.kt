package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Africa
import com.example.capitalcityquizktx.data.models.geographical.continents.Asia
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import testUtil.TestData

class GameInteractorTest : KoinTest {

    private val FIELDSCOUNT: Int = 10
    private lateinit var interactor : GameInteractor

    // Read comment below to understand why this object is not being injected
    //    private val interactor : GameInteractor by inject()
    // Also this activity is not needed
    //    @RelaxedMockK
    //    private lateinit var activity: Activity

    @MockK
    private lateinit var repository: CountryRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
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
        val slot = slot<Country>()
        every { repository.removeCountry(capture(slot)) } just Runs
        interactor.removeCountry(country)
        assertEquals(country, slot.captured)
    }

    @Test
    fun getCountriesIn_listOfContinents_returnsCountryList() {
        every { repository.getCountryListBy(any()) } returns TestData.COUNTRIES
        val actual = interactor.getCountriesIn(listOf(Europe, Africa, Asia))
        assertTrue(actual.size == TestData.COUNTRIES.size
                && actual.containsAll(TestData.COUNTRIES) && TestData.COUNTRIES.containsAll(actual))
    }

    @Test
    fun destroyCountries_callsMethodInRepository() {
        every { repository.removeCountries() } just Runs
        interactor.destroyCountries()
        verify(exactly = 1) { repository.removeCountries() }
    }

    @Test
    fun insertAllCountries_callsRepositoryWithRightParams() {
        val slot = slot<List<Country>>()
        every { repository.insertCountries(capture(slot)) } just Runs
        interactor.insertAllCountries(TestData.COUNTRIES)
        assertEquals(slot.captured, TestData.COUNTRIES)
    }

    @Test
    fun getDataFieldCount_returnsDataFieldCount() {
        every { repository.getFieldsCount() } returns FIELDSCOUNT
        val actual = interactor.getDataFieldsCount()
        assertEquals(FIELDSCOUNT, actual)
    }

    @Test
    fun getCountriesFromStream_returnsCountryList() {
        every { repository.getCountriesFromFile() } returns TestData.COUNTRIES
        val actual = interactor.getCountriesFromStream()
        assertEquals(TestData.COUNTRIES, actual)
    }

    @Test
    fun getAllCountries_returnsSingleCountryList() {
        val expected = Single.just(TestData.COUNTRIES)
        every { repository.getCountryList() } returns expected
        val actual = interactor.getAllCountries()
        assertEquals(expected, actual)
    }
}