package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "countries_table")
data class Country(
    var countryName: String,
    var capitalName: CapitalCity,
    var continent: Continent?,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null) : Parcelable{
}