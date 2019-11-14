package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/*
Temporarily disable for the implementation of Continent
 */
//@Parcelize
//@Entity(tableName = "countries")
//data class Country(
//    @PrimaryKey @NonNull                var name: String,
//    @Embedded
//    @ColumnInfo (name = "capital_city") var capitalCity: CapitalCity,
//                                        var continent: Continent?
//) : Parcelable

@Parcelize
@Entity(tableName = "countries")
data class Country(
    @PrimaryKey @NonNull                var name: String,
    @Embedded
    @ColumnInfo (name = "capital_city") var capitalCity: CapitalCity,
    var continent: Continent2?
) : Parcelable