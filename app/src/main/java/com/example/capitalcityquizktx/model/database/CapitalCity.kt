package com.example.capitalcityquizktx.model.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
data class CapitalCity (val name: String, val picture: String? = null) : City(), Parcelable {
    override val cityName: String
        get() = name
    override val cityPicture: String?
        get() = picture
}