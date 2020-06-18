package com.example.capitalcityquizktx.data.models.user

import androidx.room.*
import com.example.capitalcityquizktx.data.models.user.LearningCountry
import com.example.capitalcityquizktx.data.models.user.User

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

