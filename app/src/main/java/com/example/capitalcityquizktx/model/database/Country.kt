package com.example.capitalcityquizktx.model.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
@Entity(tableName = "countries")
data class Country(
    @ColumnInfo(name = "country_name") var countryName: String,
    @Embedded                          var capitalCity: CapitalCity,
    @Embedded                          var continent: Continent) : Parcelable {

     @Transient @PrimaryKey(autoGenerate = true)
     @ColumnInfo
     var countryId : Long = 0
}