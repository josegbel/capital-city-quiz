package com.example.capitalcityquizktx.utils

import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.continents.*

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

    Util class that helps to return a continent object depending of the value of a continent string

 */
class ContinentSelector {

    /*
        Variables used for Debugging because returning objects were flagging issues

    val europe = Continent("Europe", 50)
    val asia = Continent("Asia", 44)
    val africa = Continent("Africa", 54)
    val northAmerica = Continent("North America", 7)
    val southAmerica = Continent("South America", 28)
    val australia = Continent("Australia", 14)

    */

    fun getContinent(aString: String): Continent {
        return when (aString.trim()) {
            "Europe" -> Europe
            "Africa" -> Africa
            "Asia" -> Asia
            "Australia" -> Australia
            "North America" -> NorthAmerica
            "South America" -> SouthAmerica
            else -> throw IllegalArgumentException("Illegal continent name: $aString")
        }
    }
}
