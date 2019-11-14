package com.example.capitalcityquizktx.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity (tableName = "user_learned_joint",
         primaryKeys = [ "user_id", "learned_id"],
         foreignKeys = [
             ForeignKey(entity = User::class,
                        parentColumns = ["user_id"],
                        childColumns = ["user_id"]),
             ForeignKey(entity = Learning::class,
                        parentColumns = ["id"],
                        childColumns = ["learned_id"])]
)
class UserLearnedJoint(
    @ColumnInfo(name = "user_id")     val userId : Int,
    @ColumnInfo(name = "learned_id") val learnedId : Int
)
