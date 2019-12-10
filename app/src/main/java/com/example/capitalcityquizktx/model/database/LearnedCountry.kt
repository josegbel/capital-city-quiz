package com.example.capitalcityquizktx.model.database

import androidx.room.*

@Entity
data class LearnedCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name = "learned_user_id") val userId: Int,
    @Embedded                             val country: Country
)
