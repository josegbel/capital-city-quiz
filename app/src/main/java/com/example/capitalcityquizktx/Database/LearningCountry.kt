package com.example.capitalcityquizktx.Database

import androidx.room.*

//@Entity(foreignKeys = arrayOf(ForeignKey(
//    entity = User::class,
//    parentColumns = arrayOf("user_id"),
//    childColumns = arrayOf("user_id"))
//),
//    tableName = "learning")
@Entity
data class LearningCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name ="learning_user_id") val userId: Int,
    @Embedded                             val country: Country,
                                          val count: Int
    )