package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity (tableName = "continents")
data class Continent(
    @PrimaryKey
    @ColumnInfo(name = "continent_name")  val continentName : String,
    @ColumnInfo(name = "total_countries") val totalCountries: Int
): Parcelable