package com.example.capitalcityquizktx.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Database(entities = [Country::class,
                      LearnedCountry::class,
                      LearningCountry::class,
                      User::class],
          version = 5,
          exportSchema = true)
abstract class CountryDatabase : RoomDatabase() {

    abstract val countryDatabaseDao: CountryDatabaseDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: CountryDatabase? = null
//
//        fun getInstance(context: Context): CountryDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//
//                if (instance == null) {
//                    instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        CountryDatabase::class.java,
//                        "country_database"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//
//                    INSTANCE = instance
//                }
//
//                return instance
//            }
//        }
//    }
}