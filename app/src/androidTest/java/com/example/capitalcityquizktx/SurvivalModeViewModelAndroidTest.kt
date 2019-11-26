package com.example.capitalcityquizktx

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabase
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import net.bytebuddy.implementation.bytecode.Throw
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SurvivalModeViewModelAndroidTest {
    private lateinit var countryDao : CountryDatabaseDao
    private lateinit var db: CountryDatabase
    private lateinit var survivalModeViewModel : SurvivalModeViewModel

//    @RelaxedMockK
//    lateinit var fragment: Fragment

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
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CountryDatabase::class.java).build()
        countryDao = db.countryDatabaseDao
    }

    @After
    @Throws (IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws (Exception::class)
    fun should_write_and_read_entry_from_db(){
        val country = Country("Spain", CapitalCity("Madrid"), "Europe")

        countryDao.insertAllCountries(listOf(country))
        val countries = countryDao.getCountries()

        assertEquals(country, countries.value?.get(0))
    }
}