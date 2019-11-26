package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Europe : Continent(continentName = "Europe", totalCountries = 50){
    override fun toString(): String {
        return "Europe"
    }
}