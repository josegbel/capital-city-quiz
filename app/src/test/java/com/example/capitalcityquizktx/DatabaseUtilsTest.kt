package com.example.capitalcityquizktx

import android.database.DatabaseUtils
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Continents.Europe
import com.example.capitalcityquizktx.Database.Country
import io.mockk.every
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream

class DatabaseUtilsTest{

    @Test
    fun `Should handle conversion of inputStream to countriesList when input is right`(){
        val fakeInput =
            """Spain,Madrid,Europe,
                |France,Paris,Europe""".trimMargin()
        val targetStream = ByteArrayInputStream(fakeInput.toByteArray())
        val expected = listOf(
            Country("Spain", CapitalCity("Madrid"), Europe),
            Country("France", CapitalCity("Paris"), Europe))

        val actual = com.example.capitalcityquizktx.Utils.DatabaseUtils.fromCsvToList(targetStream)

        for(i in 0 until actual.size){
            assertEquals(actual[i].countryName, expected[i].countryName)
            assertEquals(actual[i].capitalName.name, expected[i].capitalName.name)
            assertEquals(actual[i].continent, expected[i].continent)
        }
    }
}