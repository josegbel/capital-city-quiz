package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Africa : Continent(continentName = "Africa", totalCountries = 54){
    override fun toString(): String {
        return "Africa"
    }
}