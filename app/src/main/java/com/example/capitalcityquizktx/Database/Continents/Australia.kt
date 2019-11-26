package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Australia : Continent(continentName = "Australia" ,totalCountries = 14){
    override fun toString(): String {
        return "Australia"
    }
}