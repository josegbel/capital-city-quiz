package com.example.capitalcityquizktx.utils

import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.continents.*

class ContinentSelector {

    /*
        For Debugging
     */
    val europe = Continent("Europe", 50)
    val asia = Continent("Asia", 44)
    val africa = Continent("Africa", 54)
    val northAmerica = Continent("North America", 7)
    val southAmerica = Continent("South America", 28)
    val australia = Continent("Australia", 14)

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
