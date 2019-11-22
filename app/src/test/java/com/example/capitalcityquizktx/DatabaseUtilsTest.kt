package com.example.capitalcityquizktx

import TestUtil.MainCoroutineRule
import assertk.assertThat
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Country
import com.example.capitalcityquizktx.Utils.DatabaseUtils
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayInputStream

class DatabaseUtilsTest{
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun `should convert inputStream to a list when input is right`() = coroutineRule.runBlockingTest{

        val fakeInput =
            """Spain,Madrid,Europe,
                |France,Paris,Europe""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())
        val expected = listOf(
            listOf("Spain", "Madrid", "Europe"),
            listOf("France", "Paris", "Europe")
        )

        val actual = DatabaseUtils.streamToStringList(targetStream, coroutineRule.testDispatcher)

        assertEquals(expected, actual)
    }

    @Test (expected = RuntimeException::class)
    fun `should throw exeption when inputstream is empty`(){
        val fakeInput =
            """""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())

        DatabaseUtils.streamToStringList(targetStream, coroutineRule.testDispatcher)
    }

    @Test
    fun `should convert List_List_String__ to List_Country_`() = coroutineRule.runBlockingTest {
        val stringList = listOf(
            listOf("Spain", "Madrid", "Europe"),
            listOf("France", "Paris", "Europe")
        )
        val expected = listOf(
            Country("Spain", CapitalCity("Madrid"), "Europe"),
            Country("France", CapitalCity("Paris"), "Europe")
        )

        val actual = DatabaseUtils.stringListToCountryList(stringList, coroutineRule.testDispatcher)

        assertThat{
            for (element in expected){
                val index = expected.indexOf(element)
                assertEquals(element.countryName, actual[index].countryName)
                assertEquals(element.capitalCity.name, actual[index].capitalCity.name)
                assertEquals(element.continent, actual[index].continent)
            }
        }
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