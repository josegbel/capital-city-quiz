package com.example.capitalcityquizktx

import android.app.Application
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import com.example.capitalcityquizktx.di.DatabaseModule
import com.example.capitalcityquizktx.di.GameUseCasesModule
import com.example.capitalcityquizktx.di.RepositoryModule
import com.example.capitalcityquizktx.di.SurvivalViewModelModule
import com.example.capitalcityquizktx.domain.viewmodels.SurvivalViewModel
import com.example.capitalcityquizktx.testUtil.MainCoroutineRule
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SurvivalViewModelTest : KoinTest {
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    lateinit var application: Application

    @Mock
    lateinit var dataSource: CountryDatabaseDao

    private val survivalViewModel : SurvivalViewModel by inject()
    private lateinit var mocks: AutoCloseable

    @BeforeEach
    fun setUp(){
        mocks = MockitoAnnotations.openMocks(this)

        startKoin {
            androidContext(application)
            // todo these modules need to be mocked out
            modules(listOf(
                SurvivalViewModelModule.getModule(),
                RepositoryModule.getModule(),
                GameUseCasesModule.getModules(),
                DatabaseModule.getModule()))
        }
    }

    @AfterEach
    fun tearDown(){
        stopKoin()
        mocks.close()
    }

    @Test
    fun `given a liveData that emmits when calling getCountries then shuffles the data and add to queue` (){
//        val country1 = Country("Australia", CapitalCity("Sidney"), Australia)
//        val country2 = Country("China", CapitalCity("Beijin"), Asia)
//        val country3 = Country("Senegal", CapitalCity("Dakar"), Africa)
//        val country4 = Country("Spain", CapitalCity("Madrid"), Europe)
//        val country5 = Country("Peru", CapitalCity("Lima"), SouthAmerica)
//        val country6 = Country("USA", CapitalCity("Washington"), NorthAmerica)
//        val countries = listOf(country1, country2, country3, country4, country5, country6)
//        val liveData = MutableLiveData<List<Country>>()
//        liveData.value = countries

        val data = dataSource.getCountries()

    }

}