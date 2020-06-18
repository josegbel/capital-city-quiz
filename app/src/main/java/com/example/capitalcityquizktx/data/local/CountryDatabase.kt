package com.example.capitalcityquizktx.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.capitalcityquizktx.data.models.geographical.Country
import com.example.capitalcityquizktx.data.models.user.LearnedCountry
import com.example.capitalcityquizktx.data.models.user.LearningCountry
import com.example.capitalcityquizktx.data.models.user.User

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Database(entities = [Country::class,
                      LearnedCountry::class,
                      LearningCountry::class,
                      User::class],
          version = 6,
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