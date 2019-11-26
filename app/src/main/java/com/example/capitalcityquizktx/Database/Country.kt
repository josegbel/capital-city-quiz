package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


/*
Temporarily disable for the implementation of Continent
 */
//@Parcelize
//@Entity(tableName = "countries")
//data class Country(
//    @PrimaryKey @NonNull                var countryName: String,
//    @Embedded
//    @ColumnInfo (countryName = "capital_city") var capitalCity: CapitalCity,
//                                        var continent: Continent?
//) : Parcelable

@Parcelize
//@Entity(tableName = "countries",
//        foreignKeys = [ForeignKey(
//                            entity = Continent::class,
//                            parentColumns = ["continent_name"],
//                            childColumns = ["continent"])])
@Entity(tableName = "countries")
data class Country(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "country_name") var countryName: String,
    @Embedded                          var capitalCity: CapitalCity,
    @Embedded                          var continent: Continent
) : Parcelable