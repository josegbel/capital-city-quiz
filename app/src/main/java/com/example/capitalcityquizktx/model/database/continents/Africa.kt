package com.example.capitalcityquizktx.model.database.continents

import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
object Africa : Continent(continentName = "Africa", totalCountries = 53){
    override fun toString(): String {
        return "Africa"
    }
}