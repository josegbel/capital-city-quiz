package com.example.capitalcityquizktx.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.nio.file.attribute.UserDefinedFileAttributeView

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

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertUser(user : User)

    @Query("select * from users where user_id like :id")
    fun getUserFromId(id : Int) : User

//    @Query ("insert into countries_learned")
//    fun insertLearnedCountry()
}