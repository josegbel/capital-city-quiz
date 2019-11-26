package com.example.capitalcityquizktx.Database

import androidx.room.*

@Entity
data class LearnedCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name = "learned_user_id") val userId: Int,
    @Embedded                             val country: Country
)
