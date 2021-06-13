package com.example.capitalcityquizktx

import assertk.assertThat
import com.example.capitalcityquizktx.common.utils.ContinentSelector
import com.example.capitalcityquizktx.common.utils.DatabaseUtils
import com.example.capitalcityquizktx.data.models.geographical.CapitalCity
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.geographical.continents.Europe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream

/**
 * This class tests a helper function getCountriesFromStream(...) which has been moved to
 * a CsvDataLoader class.
 */
class DatabaseUtilsTest{

    private lateinit var continentSelector : ContinentSelector

    @BeforeEach
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

    @Test
    fun `should throw exception when inputStream is empty`(){
        val fakeInput =
            """""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())

        assertThrows<RuntimeException> {
            DatabaseUtils.getCountriesFromStream(targetStream, continentSelector)
        }
    }
}