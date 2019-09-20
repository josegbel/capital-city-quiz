package com.example.capitalcityquizktx.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDatabaseDao {

//    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<Country>)

    @Query("SELECT COUNT(*) FROM countries_table")
    fun dataInDatabase() : Boolean
//    @Update
//
//    @Query

}