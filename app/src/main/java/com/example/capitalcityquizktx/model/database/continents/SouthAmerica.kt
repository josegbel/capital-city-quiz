package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object SouthAmerica : Continent(continentName = "South America", totalCountries = 28){
    override fun toString(): String {
        return "South America"
    }
}
