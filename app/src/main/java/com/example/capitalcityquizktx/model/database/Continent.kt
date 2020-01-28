package com.example.capitalcityquizktx.model.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
open class Continent(val continentName : String, val totalCountries: Int) : Parcelable
