package com.example.capitalcityquizktx.model.database

import androidx.room.*

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class UserLearnedJoint {
    @Embedded
    lateinit var user: User
    @Relation(
        parentColumn = "user_id",
        entityColumn = "learned_user_id",
        associateBy = Junction(LearnedCountryUserCrossRef::class)
    )
    lateinit var learnedCountries: List<LearnedCountry>
}
