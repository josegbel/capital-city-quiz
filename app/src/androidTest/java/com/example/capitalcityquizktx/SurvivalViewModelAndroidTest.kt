package com.example.capitalcityquizktx

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.capitalcityquizktx.androidTestUtils.MainCoroutineRule
import com.example.capitalcityquizktx.androidTestUtils.getOrAwaitValue
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.DataCsvLoader
import com.example.capitalcityquizktx.data.DataDownloader
import com.example.capitalcityquizktx.data.local.CountryDatabase
import com.example.capitalcityquizktx.data.local.CountryDatabaseDao
import com.example.capitalcityquizktx.data.models.geographical.CapitalCity
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.*
import com.example.capitalcityquizktx.data.models.user.LearnedCountry
import com.example.capitalcityquizktx.data.models.user.User
import com.example.capitalcityquizktx.domain.SurvivalGameInteractor
import com.example.capitalcityquizktx.domain.SurvivalGameUseCases
import com.example.capitalcityquizktx.domain.viewmodels.SurvivalViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import java.io.IOException

@ExperimentalCoroutinesApi
class SurvivalViewModelAndroidTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var countryDao: CountryDatabaseDao
    private lateinit var db: CountryDatabase
    private lateinit var context: Context
    private lateinit var survivalViewModel: SurvivalViewModel
    private lateinit var survivalGameUseCases: SurvivalGameUseCases
    private lateinit var countryRepository: CountryRepository

    private lateinit var mocks: AutoCloseable

    companion object {
        const val TAG: String = "JUnit4"
    }

    @BeforeEach
    fun setUp() {
        mocks = MockitoAnnotations.openMocks(this)

        context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        Log.d(TAG, "createDb")
        countryDao = db.countryDatabaseDao
        Log.d(TAG, "Dao Referenced")
        countryRepository = DataDownloader(DataCsvLoader(), countryDao, context)
        survivalGameUseCases = SurvivalGameInteractor(countryRepository)
        survivalViewModel = SurvivalViewModel(survivalGameUseCases, Dispatchers.Default)
    }

    @AfterEach
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
        Log.d(TAG, "closeDb")
        mocks.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_write_and_read_entry_from_db_in_counties_table() {
        val country =
            Country(
                "Spain",
                CapitalCity("Madrid"),
                Europe
            )
        countryDao.insertAllCountries(listOf(country))
        Log.d(TAG, "writeDb")

        val countries = countryDao.getCountries()
        Log.d(TAG, "readDatabase")

        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)
        assertEquals(country.capitalCity, countries.getOrAwaitValue()[0].capitalCity)
        assertEquals(
            country.continent.continentName,
            countries.getOrAwaitValue()[0].continent.continentName
        )
    }

    @Test
    fun given_list_continents_when_getCountriesByContinent_return_all_their_countries() {
        val continents = listOf(Europe, Africa, Asia)
        val expectedSizeList = Europe.totalCountries + Africa.totalCountries + Asia.totalCountries
        val countries: List<Country>

        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)
        countries = survivalViewModel.survivalGameUseCases.getCountriesIn(continents)
        Thread.sleep(350)

        assertEquals(expectedSizeList, countries.size)
    }

    @Test
    fun should_print_all_european_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until Europe.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Europe))[i].countryName)
    }

    @Test
    fun should_print_all_european_countries_in_shuffled_fashion() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)
        val shuffle1: List<Country> = survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Europe))
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)
        val shuffle2: List<Country> = survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Europe))

        assertNotEquals(shuffle1, shuffle2)
    }

    @Test
    fun should_print_all_north_american_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until NorthAmerica.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(NorthAmerica))[i].countryName)
    }

    @Test
    fun should_print_all_south_american_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until SouthAmerica.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(SouthAmerica))[i].countryName)
    }

    @Test
    fun should_print_all_australian_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until Australia.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Australia))[i].countryName)
    }

    @Test
    fun should_print_all_african_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until Africa.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Africa))[i].countryName)
    }

    @Test
    fun should_print_all_asian_countries() = coroutineRule.runBlockingTest {
        survivalViewModel.repopulateCountriesInCache()
        Thread.sleep(350)

        for (i in 0 until Asia.totalCountries)
            println(survivalViewModel.survivalGameUseCases.getCountriesIn(listOf(Asia))[i].countryName)
    }

    @Test
    @Throws(Exception::class)
    fun should_write_and_read_many_entries_from_db_in_counties_table() {
        val country1 =
            Country(
                "Spain",
                CapitalCity("Madrid"),
                Europe
            )
        val country2 =
            Country(
                "Senegal",
                CapitalCity("Dakar"),
                Africa
            )
        val country3 =
            Country(
                "China",
                CapitalCity("Beijin"),
                Asia
            )
        val country4 =
            Country(
                "Australia",
                CapitalCity("Sidney"),
                Australia
            )
        val country5 =
            Country(
                "USA",
                CapitalCity("Washington"),
                NorthAmerica
            )
        val country6 =
            Country(
                "Peru",
                CapitalCity("Lima"),
                SouthAmerica
            )
        val expected = listOf(
            country1, country2, country3,
            country4, country5, country6
        )
        countryDao.insertAllCountries(expected)
        Log.d(TAG, "writeDb")

        val actual = countryDao.getCountries()
        Log.d(TAG, "readDatabase")

        for ((i, country) in expected.withIndex()) {
            assertEquals(
                country.countryName, actual.getOrAwaitValue()[i].countryName
            )
            assertEquals(
                country.capitalCity, actual.getOrAwaitValue()[i].capitalCity
            )
            assertEquals(
                country.continent.continentName, actual.getOrAwaitValue()[i].continent.continentName
            )
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_write_and_read_entry_from_db_in_countriesLearned_table() {
        val country =
            Country(
                "Spain",
                CapitalCity("Madrid"),
                Europe
            )
        val user = User(
            1,
            "johnDoe",
            "pw",
            "john",
            "doe",
            "john@doe.com"
        )

        countryDao.insertLearnedCountry(
            LearnedCountry(
                user.userId,
                country
            )
        )
        Log.d(TAG, "writeToLearnedTable")
        val countries = countryDao.getLearnedCountries()
        Log.d(TAG, "readFromLearnedTable")
        assertEquals(country.countryName, countries.getOrAwaitValue()[0].country.countryName)
        assertEquals(country.capitalCity, countries.getOrAwaitValue()[0].country.capitalCity)
        assertEquals(
            country.continent.continentName,
            countries.getOrAwaitValue()[0].country.continent.continentName
        )
        assertEquals(user.userId, countries.getOrAwaitValue()[0].userId)
    }

    @Test
    fun should_delete_entries_in_countries_table() {

        // insert Country
        val country =
            Country(
                "Spain",
                CapitalCity("Madrid"),
                Europe
            )
        countryDao.insertAllCountries(listOf(country))
        Log.d(TAG, "writeDb")
        var countries = countryDao.getCountries()
        Log.d(TAG, "readDatabase")

        // assert country was correctly inserted
        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)

        // when destroy countries is called
        countryDao.destroyCountries()
        countries = countryDao.getCountries()
        val emptyList = emptyList<Country>()

        // then assert the country list from database is empty
        assertEquals(emptyList, countries.getOrAwaitValue())
    }

    @Test
    fun given_full_database_when_view_model_is_initialised_then_delete_entries_and_insert_all_again() =
        coroutineRule.runBlockingTest {
            // thread sleep is a workaround to the problem with coroutines
            survivalViewModel.survivalGameUseCases.destroyCountries()
            Thread.sleep(1000)

            val country1 =
                Country(
                    "Spain",
                    CapitalCity("Madrid"),
                    Europe
                )
            val country2 =
                Country(
                    "France",
                    CapitalCity("Paris"),
                    Europe
                )
            val country3 =
                Country(
                    "Portugal",
                    CapitalCity("Lisbon"),
                    Europe
                )
            val expectedCountries = listOf(country1, country2, country3)
            survivalViewModel.survivalGameUseCases.insertAllCountries(expectedCountries)
            Log.d(TAG, "write3EntriesInDb")
            assertEquals(3, survivalViewModel.survivalGameUseCases.getDataFieldsCount())

            survivalViewModel.repopulateCountriesInCache()
            Thread.sleep(1000)

            assertEquals(195, survivalViewModel.survivalGameUseCases.getDataFieldsCount())
        }

    fun when_database_is_populated_print_elements_for_debugging_purposes_only() =
        coroutineRule.runBlockingTest {
            survivalViewModel.repopulateCountriesInCache()
            val countriesObservable = survivalViewModel.survivalGameUseCases.getAllCountries()
            countriesObservable.subscribe { countries ->
                for (i in 0..194) {
                    println(
                        "${countries[i].countryName}," +
                                "${countries[i].capitalCity.name}," +
                                countries[i].continent.continentName
                    )
                }
            }
        }
}