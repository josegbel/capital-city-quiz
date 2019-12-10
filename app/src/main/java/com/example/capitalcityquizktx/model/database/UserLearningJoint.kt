package com.example.capitalcityquizktx.model.database

import androidx.room.*

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class UserLearningJoint {
    @Embedded
    lateinit var user: User

    @Relation(
        parentColumn = "user_id",
        entityColumn = "learning_user_id"
    )
    lateinit var learningCountries: List<LearningCountry>
}

