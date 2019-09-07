package com.example.capitalcityquizktx.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

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
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}