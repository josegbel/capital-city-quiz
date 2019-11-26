package com.example.capitalcityquizktx.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CountryDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(countries: List<Country>)

    @Query("select * from countries")
    fun getCountries() : LiveData<List<Country>>

    @Query("select COUNT(*) from countries")
    fun dataFieldsCount() : Int

    @Transaction
    @Query ("select * from users")
    fun getLearnedCountries(): List<UserLearnedJoint>

    @Transaction
    @Query ("select * from users")
    fun getLearningCountries(): List<UserLearningJoint>
}