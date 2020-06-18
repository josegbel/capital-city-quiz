package com.example.capitalcityquizktx.data.models.geographical.continents

import com.example.capitalcityquizktx.data.models.geographical.Continent
import kotlinx.android.parcel.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
object Australia : Continent(continentName = "Australia" ,totalCountries = 14){
    override fun toString(): String {
        return "Australia"
    }
}