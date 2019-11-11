package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey
    @NonNull
    var countryName: String,
    var capitalName: CapitalCity,
    var continent: Continent?) : Parcelable{
//    @PrimaryKey(autoGenerate = true)
//    var id: Int? = null) : Parcelable{
}