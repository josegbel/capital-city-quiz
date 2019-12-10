package com.example.capitalcityquizktx.model.database

import androidx.room.*

class UserLearnedJoint {
    @Embedded
    lateinit var user: User
    @Relation(
        parentColumn = "user_id",
        entityColumn = "learned_user_id"
    )
    lateinit var learnedCountries: List<LearnedCountry>
}
