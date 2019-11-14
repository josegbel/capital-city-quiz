package com.example.capitalcityquizktx.Database

import androidx.room.*

@Entity(foreignKeys = arrayOf(ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("user_id"),
    childColumns = arrayOf("user_id"))
),
    tableName = "learning")
data class Learning(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")          val id: Int,
    @ColumnInfo(name ="user_id")     val userId: Int,
                                     val country: Country,
                                     val count: Int
    )