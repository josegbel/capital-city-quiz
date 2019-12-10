package com.example.capitalcityquizktx.model.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
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
    fun getLearnedCountries(): LiveData<UserLearnedJoint>

    @Transaction
    @Query ("select * from users")
    fun getLearningCountries(): LiveData<List<UserLearningJoint>>

    @Query ("delete from countries")
    fun destroyCountries()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user : User)

    @Query("select * from users where user_id like :id")
    fun getUserFromId(id : Int) : User

    @Transaction
    @Query("select * from users")
    fun getUserLearnedCountries(): List<UserLearnedJoint>

//    @Query ("insert into countries_learned")
//    fun insertLearnedCountry()
}