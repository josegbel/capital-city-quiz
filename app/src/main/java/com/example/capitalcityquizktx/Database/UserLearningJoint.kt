package com.example.capitalcityquizktx.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity (tableName = "user_learning_joint",
         primaryKeys = [ "user_id", "learning_id"],
         foreignKeys = [
             ForeignKey(entity = User::class,
                        parentColumns = ["user_id"],
                        childColumns = ["user_id"]),
             ForeignKey(entity = Learning::class,
                        parentColumns = ["id"],
                        childColumns = ["learning_id"])]
)
class UserLearningJoint(
    @ColumnInfo(name = "user_id")     val userId : Int,
    @ColumnInfo(name = "learning_id") val learningId : Int
)
