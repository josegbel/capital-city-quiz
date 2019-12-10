package com.example.capitalcityquizktx.di

import android.content.Context
import androidx.room.Room
import com.example.capitalcityquizktx.model.database.CountryDatabase
import com.example.capitalcityquizktx.model.database.CountryDatabaseDao
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object DatabaseModule {
    fun getModule() : Module = module {
        fun provideDatabase(context: Context): CountryDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CountryDatabase::class.java,
                "country_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideDao(database: CountryDatabase): CountryDatabaseDao {
            return database.countryDatabaseDao
        }

        single { provideDatabase(androidContext()) }
        single { provideDao(get())}
    }
}