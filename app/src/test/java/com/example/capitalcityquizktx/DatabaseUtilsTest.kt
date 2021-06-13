package com.example.capitalcityquizktx

import assertk.assertThat
import com.example.capitalcityquizktx.data.models.geographical.CapitalCity
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.common.utils.ContinentSelector
import com.example.capitalcityquizktx.common.utils.DatabaseUtils
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream

/**
 * This class tests a helper function getCountriesFromStream(...) which has been moved to
 * a CsvDataLoader class.
 */
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
            Country(
                "Spain",
                CapitalCity(
                    "Madrid"
                ),
                Europe
            ),
            Country(
                "France",
                CapitalCity(
                    "Paris"
                ),
                Europe
            )
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
    fun `should throw exception when inputStream is empty`(){
        val fakeInput =
            """""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())

        DatabaseUtils.getCountriesFromStream(targetStream, continentSelector)
    }
}