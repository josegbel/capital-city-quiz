package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import assertk.assertThat
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Continents.Europe
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Utils.ContinentSelector
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayInputStream

class DatabaseUtilsTest{

    lateinit var continentSelector : ContinentSelector

    @Before
    fun setUp(){
        continentSelector = ContinentSelector()
    }

    @Test
    fun `should convert inputStream to a list when input is right`(){
        val fakeInput =
            """Spain,Madrid,Europe,
                |France,Paris,Europe""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())
        val expected = listOf(
            Country("Spain", CapitalCity("Madrid"), Europe),
            Country("France", CapitalCity("Paris"), Europe)
        )

        val actual = DatabaseUtils.getCountriesFromStream(targetStream, continentSelector)

        assertThat{
            for (element in expected){
                val index = expected.indexOf(element)
                assertEquals(element.countryName, actual[index].countryName)
                assertEquals(element.capitalCity.name, actual[index].capitalCity.name)
                assertEquals(element.continent, actual[index].continent)
            }
        }
    }

    @Test (expected = RuntimeException::class)
    fun `should throw exeption when inputstream is empty`(){
        val fakeInput =
            """""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())

        DatabaseUtils.getCountriesFromStream(targetStream, continentSelector)
    }

//
//    @Test
//    fun `Should handle conversion of inputStream to countriesList when input is right`(){
//        val fakeInput =
//            """Spain,Madrid,Europe,
//                |France,Paris,Europe""".trimMargin()
//        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())
//        val expected = listOf(
//            Country("Spain", CapitalCity("Madrid"), Europe),
//            Country("France", CapitalCity("Paris"), Europe))
//
//        val actual = com.example.capitalcityquizktx.Utils.DatabaseUtils.fromCsvToList(targetStream)
//
//        for(i in 0 until actual.size){
//            assertEquals(actual[i].countryName, expected[i].countryName)
//            assertEquals(actual[i].capitalCity.name, expected[i].capitalCity.name)
//            assertEquals(actual[i].continent, expected[i].continent)
//        }
//    }
}