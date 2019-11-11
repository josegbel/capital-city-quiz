package com.example.capitalcityquizktx.Utils

import androidx.room.TypeConverter
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Continent
import com.example.capitalcityquizktx.Database.Continents.*
import com.example.capitalcityquizktx.Database.Country
import java.io.*

class DatabaseUtils {
    companion object {

        fun fromCsvToList(file: InputStream): List<Country> {
            val countries: ArrayList<Country> = arrayListOf()
            try {
                val reader = InputStreamReader(file)
                val buffer = BufferedReader(reader)
//                var line = ""
                var line : String?
                line = buffer.readLine()
                loop@ while(line != null){
                //TODO DO IN THE BACKGROUND
//                while (buffer.readLine() != null) {
                    val str = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    lateinit var continent: Continent
                    when (str[2]) {
                        "Asia" -> continent = Asia
                        "Europe" -> continent = Europe
                        "Australia" -> continent = Australia
                        "South America" -> continent = SouthAmerica
                        "North America" -> continent = NorthAmerica
                        else -> continue@loop
                    }
                    val country = Country(str[0], CapitalCity(str[1]), continent)
                    countries.add(country)
                    line = buffer.readLine()
                }


            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return countries
        }
    }
}

class Converters {

    @TypeConverter
    fun toCapitalCity(stringified: String): CapitalCity {
        return CapitalCity.createFromString(stringified)
    }

    @TypeConverter
    fun fromCapitalCity(cc: CapitalCity): String {
        return cc.stringify()
    }

    @TypeConverter
    fun toContinent(stringified: String): Continent {
        return Continent.createFromString(stringified)
    }

    @TypeConverter
    fun fromContinent(c: Continent): String {
        return c.stringify()
    }
}
