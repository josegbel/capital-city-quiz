package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object SouthAmerica : Continent(continentName = "South America", totalCountries = 28){
    override fun toString(): String {
        return "South America"
    }
}
