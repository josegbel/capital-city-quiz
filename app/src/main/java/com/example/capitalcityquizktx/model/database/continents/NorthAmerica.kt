package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object NorthAmerica : Continent(continentName = "North America", totalCountries = 7){
    override fun toString(): String {
        // TODO hardcoded string. Change all of the continents ones
        return "North America"
    }
}