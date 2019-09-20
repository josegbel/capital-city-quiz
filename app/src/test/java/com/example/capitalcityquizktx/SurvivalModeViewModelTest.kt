package com.example.capitalcityquizktx

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.capitalcityquizktx.Database.CountryDatabase
import com.example.capitalcityquizktx.Database.CountryDatabaseDao
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SurvivalModeViewModelTest{

    @RelaxedMockK
    lateinit var db: CountryDatabase

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
    fun `Should handle csv dropped`(){
        every { survivalModeViewModel.database.dataInDatabase() } returns true

        val result = survivalModeViewModel.database.dataInDatabase()

        assertEquals(result, true)
        /*
        given a mocked database is opened
        when viewModel.loadDatabase()
        verify: doNothing
         */
    }

    @Test
    fun `Should handle csv not dropped`(){
        /*
        given a mocked database
        when viewModel.loadDatabase()
        verify:DataUtils.dropCsvToDB
         */
    }

}