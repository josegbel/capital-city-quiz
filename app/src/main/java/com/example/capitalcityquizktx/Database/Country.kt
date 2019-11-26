package com.example.capitalcityquizktx.Database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "countries")
data class Country(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "country_name") var countryName: String,
    @Embedded                          var capitalCity: CapitalCity,
    @Embedded                          var continent: Continent
) : Parcelable