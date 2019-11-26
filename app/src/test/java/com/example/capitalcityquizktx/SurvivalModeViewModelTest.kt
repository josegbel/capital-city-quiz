package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Database.CountryDatabase
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.InputStream

class SurvivalModeViewModelTest{
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    lateinit var db: CountryDatabase

    @RelaxedMockK
    lateinit var targetStream : InputStream

    @RelaxedMockK
    lateinit var activity: Activity

    @RelaxedMockK
    lateinit var dataSource: CountryDatabaseDao

    @RelaxedMockK
    lateinit var fragment: Fragment

    private lateinit var survivalModeViewModel : SurvivalModeViewModel

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        val application = requireNotNull(activity).application

        val viewModelFactory = SurvivalModeViewModelFactory(dataSource, application)

        survivalModeViewModel = ViewModelProviders.of(fragment, viewModelFactory).get(SurvivalModeViewModel::class.java)
    }

    @Test
    fun `Should handle not empty database`(){
        every { survivalModeViewModel.database.dataFieldsCount() } returns 1

        val result = survivalModeViewModel.database.dataFieldsCount()

        assertNotSame(result, 0)
        /*
        given a mocked database is opened
        when viewModel.loadDatabase()
        verify: doNothing
         */
    }

    @Test
    fun `Should populate empty database`() = coroutineRule.runBlockingTest{
//
//        val reader = StringReader(fakeInput)
//        val fakeStream = BufferedReader(reader)

//        every { survivalModeViewModel.database.dataFieldsCount()
//        } returns 0
        every { DatabaseUtils.getCountriesFromStream(targetStream) } returns listOf(
            Country("Spain", CapitalCity("Madrid"), "Europe"),
            Country("France", CapitalCity("Paris"), "Europe")
        )

        survivalModeViewModel.populateDatabase()

        val result = survivalModeViewModel.database.dataFieldsCount()

        assertEquals(2, result)

//        assertNotSame(survivalModeViewModel.database.dataFieldsCount(), 0)
//        verify {
//            val countries = DatabaseUtils.getCountriesFromStream(targetStream, coroutineRule.testDispatcher)
//            survivalModeViewModel.database.insertAllCountries(countries)
//        }
//        assertNotSame(survivalModeViewModel.database.dataFieldsCount(), 0)



//        every{ survivalModeViewModel.database.dataFieldsCount() } returns 197
//
//        survivalModeViewModel.populateDatabase()
//
//        assertEquals(197, survivalModeViewModel.database.dataFieldsCount())

    }

}