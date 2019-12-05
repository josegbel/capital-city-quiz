package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.InputStream

class SurvivalModeViewModelTest{
    @get:Rule
    val coroutineRule = MainCoroutineRule()

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
        every { survivalModeViewModel.database.dataFieldsCount() } returns 197

        val result = survivalModeViewModel.shouldPopulate()

        assertEquals(false, result)
     //   verify(exactly = 0) { survivalModeViewModel.populateDatabase() }
    }

    @Test
    fun `Should populate empty database`() = coroutineRule.runBlockingTest{
        every { survivalModeViewModel.database.dataFieldsCount() } returns 0

        val result = survivalModeViewModel.shouldPopulate()

        assertEquals(true, result)
       // verify { survivalModeViewModel.populateDatabase() }
    }

    @Test
    fun `given a liveData that emmits when calling getCountries then shuffles the data` (){
        val data = dataSource.getCountries()
    }
}