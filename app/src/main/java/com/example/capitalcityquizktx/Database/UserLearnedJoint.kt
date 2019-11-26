package com.example.capitalcityquizktx.Database

import androidx.room.*

//@Entity (tableName = "user_learned_joint",
//         primaryKeys = [ "user_id", "learned_id"],
//         foreignKeys = [
//             ForeignKey(entity = User::class,
//                        parentColumns = ["user_id"],
//                        childColumns = ["user_id"]),
//             ForeignKey(entity = Learning::class,
//                        parentColumns = ["id"],
//                        childColumns = ["learned_id"])]
//)
class UserLearnedJoint {
    @Embedded
    lateinit var user: User
    @Relation(
        parentColumn = "user_id",
        entityColumn = "learned_user_id"
    )
    lateinit var learnedCountries: List<LearnedCountry>
}
