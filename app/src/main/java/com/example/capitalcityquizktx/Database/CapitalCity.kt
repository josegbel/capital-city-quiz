package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CapitalCity (
    val name: String,
    val picture: String? = null)
    : City(), Parcelable {
    override val cityName: String
        get() = name
    override val cityPicture: String?
        get() = picture
}