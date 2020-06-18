package com.example.capitalcityquizktx.data.models.user

import androidx.room.*
import com.example.capitalcityquizktx.data.models.geographical.Country

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Entity(tableName = "learned_countries")
data class LearnedCountry(
    @ColumnInfo(name = "learned_user_id") val userId: Long,
    @Embedded                             val country: Country
){
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               var id : Long = 0L

}
