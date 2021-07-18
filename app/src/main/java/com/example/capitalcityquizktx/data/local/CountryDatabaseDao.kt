package com.example.capitalcityquizktx.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.user.LearnedCountry
import com.example.capitalcityquizktx.data.models.user.User
import com.example.capitalcityquizktx.data.models.user.UserLearnedJoint
import com.example.capitalcityquizktx.data.models.user.UserLearningJoint

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Dao
interface CountryDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCountries(countries: List<Country>)

    @Query("select * from countries")
    fun getCountries() : LiveData<List<Country>>

    @Query("SELECT * FROM countries WHERE continentName LIKE :continent")
    fun getCountriesBy(continent: String) : MutableList<Country>

    @Query("select COUNT(*) from countries")
    fun dataFieldsCount() : Int

    // FIXME CREATE LEARNED COUNTRIES REPOSITORY
    @Transaction
    @Query ("select * from learned_countries")
    fun getLearnedCountries(): LiveData<List<LearnedCountry>>

    @Transaction
    @Query ("select * from users")
    fun getLearningCountries(): LiveData<List<UserLearningJoint>>

    @Query ("delete from countries")
    fun destroyCountries()

    // FIXME CREATE USER REPOSITORY
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user : User)

    @Query("select * from users where user_id like :id")
    fun getUserFromId(id : Long) : User

    @Transaction
    @Query("select * from users")
    fun getUserLearnedCountries(): List<UserLearnedJoint>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertLearnedCountry(learnedCountry: LearnedCountry)
}