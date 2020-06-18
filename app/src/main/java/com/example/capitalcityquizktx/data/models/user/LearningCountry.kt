package com.example.capitalcityquizktx.data.models.user

import androidx.room.*
import com.example.capitalcityquizktx.data.models.geographical.Country

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Entity
data class LearningCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name ="learning_user_id") val userId: Int,
    @Embedded                             val country: Country,
    val count: Int
    )