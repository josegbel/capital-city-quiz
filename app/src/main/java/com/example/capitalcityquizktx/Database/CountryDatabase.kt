package com.example.capitalcityquizktx.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.capitalcityquizktx.Utils.Converters

@Database(entities = [Country::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
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