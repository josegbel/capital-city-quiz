package com.example.capitalcityquizktx

import TestUtils.getOrAwaitValue
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Continents.Europe
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabase
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(AndroidJUnit4::class)
class SurvivalModeViewModelAndroidTest {

    private lateinit var countryDao : CountryDatabaseDao
    private lateinit var db: CountryDatabase
    private lateinit var survivalModeViewModel : SurvivalModeViewModel

    companion object{
        const val TAG : String = "JUnit4"
    }

    @Before
    fun setUp() {
//        MockKAnnotations.init(this)
        //val application = requireNotNull(activity).application
        // get ViewModel
//        val viewModelFactory =
//            SurvivalModeViewModelFactory(countryDao, ApplicationProvider.getApplicationContext())
//        survivalModeViewModel =
//            ViewModelProviders.of(fragment, viewModelFactory).get(SurvivalModeViewModel::class.java)

        // Create database
//        val context = ApplicationProvider.getApplicationContext<Context>()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        Log.d(TAG, "createDb")
        countryDao = db.countryDatabaseDao
        Log.d(TAG, "Dao Referenced")
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
    fun should_write_and_read_entry_from_db_in_countiesLearned_table(){
        val country = Country("Spain", CapitalCity("Madrid"), Europe)
        Log.d(TAG, "writeDb")
        val countries = countryDao.getCountries()
        Log.d(TAG, "readDatabase")
        assertEquals(country.countryName, countries.getOrAwaitValue()[0].countryName)
        assertEquals(country.capitalCity, countries.getOrAwaitValue()[0].capitalCity)
        assertEquals(country.continent.continentName, countries.getOrAwaitValue()[0].continent.continentName)
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

        countryDao.destroyCountriesTable()
        countries = countryDao.getCountries()
        val emptyList = emptyList<Country>()

        assertEquals(emptyList ,countries.getOrAwaitValue())
    }
}