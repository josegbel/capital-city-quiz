package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.common.utils.ContinentSelector
import com.example.capitalcityquizktx.data.models.geographical.continents.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ContinentSelectorTest {

    @Test
    fun `when Europe is passed as String should return Europe object` (){
        // given
        val aString = "Europe"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, Europe)
    }

    @Test
    fun `when Asia is passed as String should return Asia object` (){
        // given
        val aString = "Asia"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, Asia)
    }

    @Test
    fun `when North America is passed as String should return NorthAmerica object` (){
        // given
        val aString = "North America"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, NorthAmerica)
    }

    @Test
    fun `when South America is passed as String should return SouthAmerica object` (){
        // given
        val aString = "South America"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, SouthAmerica)
    }

    @Test
    fun `when Australia is passed as String should return Australia object` (){
        // given
        val aString = "Australia"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, Australia)
    }

    @Test
    fun `when Africa is passed as String should return Africa object` (){
        // given
        val aString = "Africa"
        val continentSelector = ContinentSelector()

        // when
        val continent = continentSelector.getContinent(aString)

        // then
        assertEquals (continent, Africa)
    }

    @Test
    fun `when wrong input is passed as String should throw Exception` (){
        // given
        val aString = "Euro"
        val continentSelector = ContinentSelector()

        assertThrows<java.lang.IllegalArgumentException>{
            // when
            continentSelector.getContinent(aString)
        }
    }
}