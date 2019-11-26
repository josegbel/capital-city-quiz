package com.example.capitalcityquizktx.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")     val userId: Int,
    @ColumnInfo(name = "user_name")   val userName: String,
                                      val password: String,
    @ColumnInfo(name = "first_name")  val firstName: String,
    @ColumnInfo(name = "last_name")   val lastName: String,
                                      val email: String
)