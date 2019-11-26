package com.example.capitalcityquizktx.Database

import androidx.room.*

//@Entity(
//    foreignKeys = arrayOf(ForeignKey(
//            entity = User::class,
//            parentColumns = arrayOf("user_id"),
//            childColumns = arrayOf("user_id")
//        )
//    ),
//    tableName = "learned"
//    //, primaryKey is now "id", not composite
////    primaryKeys = ["user_id", "country"]
//)
@Entity
data class LearnedCountry(
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name ="id")               val id: Int,
    @ColumnInfo(name = "learned_user_id") val userId: Int,
    @Embedded                             val country: Country
)
