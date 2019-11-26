package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object NorthAmerica : Continent(continentName = "NorthAmerica", totalCountries = 7){
    override fun toString(): String {
        // TODO hardcoded string. Change all of the continents ones
        return "North America"
    }
}