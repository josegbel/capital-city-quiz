package com.example.capitalcityquizktx.model.database

import androidx.room.*

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Entity
data class LearnedCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Long,
    @ColumnInfo(name = "learned_user_id") val userId: Long,
    @Embedded                             val country: Country
)
