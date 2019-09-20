package com.example.capitalcityquizktx.Database.Repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.capitalcityquizktx.R
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class CountriesRepository() {

    fun dropCsvDataToDb(c: Context, db: SQLiteDatabase) {
        try {
            val file = c.resources.openRawResource(R.raw.allcountries)
            val reader = InputStreamReader(file)
            val buffer = BufferedReader(reader)
            var line = ""
            val tableName = "COUNTRIES"
            val columns = "NAME, CAPITAL, CONTINENT"
            val str1 = "INSERT INTO $tableName ($columns) values("
            val str2 = ");"

            db.beginTransaction()

//            while ((line = buffer.readLine()) != null) {
            while (buffer.readLine() != null) {
                line = buffer.readLine()
                val sb = StringBuilder(str1)
                val str = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                sb.append("'" + str[0] + "','")
                sb.append(str[1] + "','")
                sb.append(str[2] + "'")
                sb.append(str2)
                db.execSQL(sb.toString())
            }

            db.setTransactionSuccessful()
            db.endTransaction()

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

}