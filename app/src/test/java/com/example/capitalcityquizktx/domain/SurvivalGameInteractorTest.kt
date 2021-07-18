package com.example.capitalcityquizktx.domain

import android.app.Application
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Africa
import com.example.capitalcityquizktx.data.models.geographical.continents.Asia
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import com.example.capitalcityquizktx.di.SurvivalGameUseCasesModule
import com.example.capitalcityquizktx.testUtil.TestData
import io.reactivex.Single
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
import org.mockito.kotlin.*
import org.mockito.stubbing.Answer

class SurvivalGameInteractorTest : KoinTest {

    companion object {
        private const val FIELD_COUNT = 10
    }

    private val interactor: SurvivalGameUseCases by inject()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var repositoryMock: CountryRepository

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
            single { repositoryMock }
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
                    SurvivalGameUseCasesModule.getModules()
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
    fun getCountriesIn_listOfContinents_returnsCountryList() {
        whenever(repositoryMock.getCountryListBy(any())).thenReturn(TestData.COUNTRIES)
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
        whenever(repositoryMock.removeCountries()).thenAnswer { Answer { } }
        interactor.destroyCountries()
        verify(repositoryMock, Times(1)).removeCountries()
    }

    @Test
    fun insertAllCountries_callsRepositoryWithRightParams() {
        whenever(repositoryMock.insertCountries(any())).thenAnswer { Answer { emptyList<Country>() } }
        interactor.insertAllCountries(TestData.COUNTRIES)
        verify(repositoryMock, Times(1)).insertCountries(countryListCaptor.capture())
        assertEquals(countryListCaptor.firstValue, TestData.COUNTRIES)
    }

    @Test
    fun getDataFieldCount_returnsDataFieldCount() {
        whenever(repositoryMock.getFieldsCount()).thenReturn(FIELD_COUNT)
        val actual = interactor.getDataFieldsCount()
        assertEquals(FIELD_COUNT, actual)
    }

    @Test
    fun getCountriesFromStream_returnsCountryList() {
        whenever(repositoryMock.getCountriesFromFile()).thenReturn(TestData.COUNTRIES)
        val actual = interactor.getCountriesFromFile()
        assertEquals(TestData.COUNTRIES, actual)
    }

    @Test
    fun getAllCountries_returnsSingleCountryList() {
        val expected = Single.just(TestData.COUNTRIES)
        whenever(repositoryMock.getCountryList()).thenReturn(expected)
        val actual = interactor.getAllCountries()
        assertEquals(expected, actual)
    }

    @Test
    fun somePositiveIntegerValue_getCountriesInTake_returnsSingleCountryList() {
        val expected = Single.just(TestData.COUNTRIES)
        whenever(repositoryMock.getCountryList()).thenReturn(expected)
        val actual = interactor.getAllCountries()
        assertEquals(expected, actual)
    }
}
