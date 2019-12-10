package com.example.capitalcityquizktx

import AndroidTestUtils.MainCoroutineRule
import AndroidTestUtils.getOrAwaitValue
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.capitalcityquizktx.model.database.CapitalCity
import com.example.capitalcityquizktx.model.database.continents.*
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.CountryDatabase
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SurvivalViewModelAndroidTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var countryDao : CountryDatabaseDao
    private lateinit var db: CountryDatabase
    private lateinit var survivalModeViewModel : SurvivalModeViewModel
    private lateinit var context : Context

    companion object{
        const val TAG : String = "JUnit4"
    }

    @Before
    fun setUp() {
        // Create database
        context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        Log.d(TAG, "createDb")
        countryDao = db.countryDatabaseDao
        Log.d(TAG, "Dao Referenced")

//        val application = requireNotNull(activity).application
    }

    @After
    @Throws (IOException::class)
    fun closeDb() {
        db.close()
        Log.d(TAG, "closeDb")
    }

    @Test
    @Throws (Exception::class)
    fun should_write_and_read_entry_from_db_in_counties_table(){
        val country = Country("Spain", CapitalCity("Madrid"), Europe)
        countryDao.insertAllCountries(listOf(country))
        Log.d(TAG, "writeDb")

        val countries = countryDao.getCountries()
        Log.d(TAG, "readDatabase")

        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)
        assertEquals(country.capitalCity, countries.getOrAwaitValue()[0].capitalCity)
        assertEquals(country.continent.continentName, countries.getOrAwaitValue()[0].continent.continentName)
    }

    @Test
    @Throws (Exception::class)
    fun should_write_and_read_many_entries_from_db_in_counties_table(){
        val country1 = Country("Spain", CapitalCity("Madrid"), Europe)
        val country2 = Country("Senegal", CapitalCity("Dakar"), Africa)
        val country3 = Country("China", CapitalCity("Beijin"), Asia)
        val country4 = Country("Australia", CapitalCity("Sidney"), Australia)
        val country5 = Country("USA", CapitalCity("Washington"), NorthAmerica)
        val country6 = Country("Peru", CapitalCity("Lima"), SouthAmerica)
        val expected = listOf(country1, country2, country3,
                                            country4, country5, country6)
        countryDao.insertAllCountries(expected)
        Log.d(TAG, "writeDb")

        val actual = countryDao.getCountries()
        Log.d(TAG, "readDatabase")

        for ((i, country) in expected.withIndex()){
            assertEquals(
                country.countryName, actual.getOrAwaitValue()[i].countryName)
            assertEquals(
                country.capitalCity, actual.getOrAwaitValue()[i].capitalCity)
            assertEquals(
                country.continent.continentName, actual.getOrAwaitValue()[i].continent.continentName
            )
        }
    }


    @Test
    @Throws (Exception::class)
    fun should_write_and_read_entry_from_db_in_countriesLearned_table(){
        val country = Country("Spain", CapitalCity("Madrid"), Europe)
//        countryDao.insertLearnedCountry()
        Log.d(TAG, "writeToLeanedTable")
        val countries = countryDao.getLearnedCountries()
        Log.d(TAG, "readFromLearnedTable")
        assertEquals(country.countryName, countries.getOrAwaitValue().learnedCountries[0].country.countryName)
        assertEquals(country.capitalCity, countries.getOrAwaitValue().learnedCountries[0].country.capitalCity)
        assertEquals(country.continent.continentName, countries.getOrAwaitValue().learnedCountries[0].country.continent.continentName)
    }

    @Test
    fun should_delete_entries_in_countries_table(){
        // insert Country
        val country = Country("Spain", CapitalCity("Madrid"), Europe)
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
        assertEquals(emptyList ,countries.getOrAwaitValue())
    }

    @Test
    fun given_full_database_when_view_model_is_initialise_then_delete_entries_and_insert_all_again() = coroutineRule.runBlockingTest{

        // get ViewModel
        val application = ApplicationProvider.getApplicationContext<Application>()
        survivalModeViewModel = SurvivalModeViewModel(countryDao, application , TestCoroutineDispatcher())

        val country1 = Country("Spain", CapitalCity("Madrid"), Europe)
        val country2 = Country("France", CapitalCity("Paris"), Europe)
        val country3 = Country("Portugal", CapitalCity("Lisbon"), Europe)
        val expectedCountries = listOf(country1, country2, country3)
        countryDao.insertAllCountries(expectedCountries)
        Log.d(TAG, "write3EntriesInDb")
        assertEquals(3, survivalModeViewModel.database.dataFieldsCount())

        survivalModeViewModel.populateDatabase()

        assertEquals(195, survivalModeViewModel.database.dataFieldsCount())
    }

    fun when_database_is_populated_print_elements_for_debugging_purposes_only() {
        survivalModeViewModel.populateDatabase()
        val countries = survivalModeViewModel.database.getCountries()

        for (i in 0..194){
            println("${countries.getOrAwaitValue()[i].countryName}," +
                    "${countries.getOrAwaitValue()[i].capitalCity.name}," +
                    "${countries.getOrAwaitValue()[i].continent.continentName}")
        }
    }
}