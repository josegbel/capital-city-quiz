package com.example.capitalcityquizktx.Database.Continents

import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
object Asia : Continent(continentName = "Asia", totalCountries = 44){
    override fun toString(): String {
        //TODO Find a way to enable locale translation feature to this method. getStringById(R.id...)
        return "Asia"
    }
}