package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Australia : Continent(continentName = "Australia" ,totalCountries = 14){
    override fun toString(): String {
        return "Australia"
    }
}