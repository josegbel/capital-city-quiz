package com.example.capitalcityquizktx.model.database

import androidx.room.*

@Entity
data class LearningCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name ="learning_user_id") val userId: Int,
    @Embedded                             val country: Country,
                                          val count: Int
    )