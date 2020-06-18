package com.example.capitalcityquizktx.data.models.user

import androidx.room.*
import com.example.capitalcityquizktx.data.models.user.LearnedCountry
import com.example.capitalcityquizktx.data.models.user.User

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class UserLearnedJoint {
    @Embedded
    lateinit var user: User
    @Relation(
        parentColumn = "user_id",
        entityColumn = "learned_user_id"
      //  associateBy = Junction(LearnedCountryUserCrossRef::class)
    )
    lateinit var learnedCountries: List<LearnedCountry>
}
