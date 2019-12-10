package com.example.capitalcityquizktx.model.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
@Entity(tableName = "countries")
data class Country(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "country_name") var countryName: String,
    @Embedded                          var capitalCity: CapitalCity,
    @Embedded                          var continent: Continent
) : Parcelable