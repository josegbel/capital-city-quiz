package com.example.capitalcityquizktx.model.database

import androidx.room.Entity

@Entity(primaryKeys = ["country_name", "user_id"])
data class LearnedCountryUserCrossRef(
    val countryLearned: String,
    val userId: Long
)