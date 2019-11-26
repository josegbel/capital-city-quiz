package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Continent(
    val continentName : String,
    val totalCountries: Int
) : Parcelable

