package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Africa : Continent(continentName = "Africa", totalCountries = 54){
    override fun toString(): String {
        return "Africa"
    }
}