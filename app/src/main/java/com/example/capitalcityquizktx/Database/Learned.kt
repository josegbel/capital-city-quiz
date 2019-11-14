package com.example.capitalcityquizktx.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = arrayOf(ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("user_id"),
            childColumns = arrayOf("user_id")
        )
    ),
    tableName = "learned",
    primaryKeys = ["user_id", "country"]
)
data class Learned(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")         val id: Int,
    @ColumnInfo(name = "user_id")   val userId: Int,
                                    val country: Country
)
