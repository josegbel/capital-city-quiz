package com.example.capitalcityquizktx.Utils

import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.Database.Continents.*

class ContinentSelector {
    fun getContinent(aString: String): Continent {
        return when(aString){
            "Europe" -> Europe
            "Africa" -> Africa
            "Asia" -> Asia
            "North America" -> NorthAmerica
            "South America" -> SouthAmerica
            "Australia" -> Australia
            else -> throw IllegalArgumentException("Illegal continent name")
        }
    }

}
