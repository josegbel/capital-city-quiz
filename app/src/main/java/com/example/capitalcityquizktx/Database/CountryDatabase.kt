package com.example.capitalcityquizktx.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Country::class,
                      LearnedCountry::class,
                      LearningCountry::class,
                      User::class],
          version = 4,
          exportSchema = true)
abstract class CountryDatabase : RoomDatabase() {

    abstract val countryDatabaseDao: CountryDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CountryDatabase? = null

        fun getInstance(context: Context): CountryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        CountryDatabase::class.java,
                        "country_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}