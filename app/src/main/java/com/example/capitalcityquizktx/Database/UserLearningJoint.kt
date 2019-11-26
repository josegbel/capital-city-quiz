package com.example.capitalcityquizktx.Database

import androidx.room.*

class UserLearningJoint {
    @Embedded
    lateinit var user: User

    @Relation(
        parentColumn = "user_id",
        entityColumn = "learning_user_id"
    )
    lateinit var learningCountries: List<LearningCountry>
}

