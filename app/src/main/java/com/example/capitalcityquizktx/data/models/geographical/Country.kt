package com.example.capitalcityquizktx.data.models.geographical

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.capitalcityquizktx.data.models.geographical.CapitalCity
import com.example.capitalcityquizktx.data.models.geographical.Continent
import kotlinx.parcelize.Parcelize

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
@Entity(tableName = "countries")
data class Country(
    @ColumnInfo(name = "country_name") var countryName: String,
    @Embedded                          var capitalCity: CapitalCity,
    @Embedded                          var continent: Continent
) : Parcelable {

     @Transient @PrimaryKey(autoGenerate = true)
     @ColumnInfo
     var countryId : Long = 0
}