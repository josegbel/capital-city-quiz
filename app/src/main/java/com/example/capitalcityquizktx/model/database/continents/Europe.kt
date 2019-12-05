package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Europe : Continent(continentName = "Europe", totalCountries = 50){
    override fun toString(): String {
        return "Europe"
    }
}