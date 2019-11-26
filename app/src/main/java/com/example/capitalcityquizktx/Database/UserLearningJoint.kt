package com.example.capitalcityquizktx.Database

import androidx.room.*

//@Entity (tableName = "user_learning_joint",
//         primaryKeys = [ "user_id", "learning_id"],
//         foreignKeys = [
//             ForeignKey(entity = User::class,
//                        parentColumns = ["user_id"],
//                        childColumns = ["user_id"]),
//             ForeignKey(entity = Learning::class,
//                        parentColumns = ["id"],
//                        childColumns = ["learning_id"])]
//)
class UserLearningJoint {
    @Embedded
    lateinit var user: User

    @Relation(
        parentColumn = "user_id",
        entityColumn = "learning_user_id"
    )
    lateinit var learningCountries: List<LearningCountry>
}

