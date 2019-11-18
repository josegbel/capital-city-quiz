package com.example.capitalcityquizktx.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDatabaseDao {

//    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(countries: List<Country>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContinents(continents: List<Continent>)

    @Query("SELECT COUNT(*) FROM countries")
    fun dataFieldsCount() : Int
//    @Update
//
//    @Query

}