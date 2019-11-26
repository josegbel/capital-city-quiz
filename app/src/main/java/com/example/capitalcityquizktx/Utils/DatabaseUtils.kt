package com.example.capitalcityquizktx.Utils
import androidx.room.TypeConverter
import com.example.capitalcityquizktx.Database.CapitalCity
import com.example.capitalcityquizktx.Database.Country
import kotlinx.coroutines.*
import java.io.*
import kotlin.coroutines.CoroutineContext
import kotlin.collections.emptyList as emptyList1

class DatabaseUtils{

    companion object {

        fun getCountriesFromStream(file: InputStream): List<Country> {
            val countries : MutableList<Country> = mutableListOf()
                try {
                    val reader = InputStreamReader(file)
                    val buffer = BufferedReader(reader)
                    var line: String?
                    line = buffer.readLine()
                    loop@ while (line != null) {
                        val str = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        countries.add(Country(str[0], CapitalCity(str[1]), str[2]))
                        line = buffer.readLine()
                    }
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            if (countries.equals(emptyList1<Country>()))
                throw RuntimeException("Country file is empty")
            return countries
        }

//        fun fromCsvToList(file: InputStream): List<Country> {
//            val countries: ArrayList<Country> = arrayListOf()
//            try {
//                val reader = InputStreamReader(file)
//                val buffer = BufferedReader(reader)
////                var line = ""
//                var line : String?
//                line = buffer.readLine()
//                loop@ while(line != null){
//                    //TODO DO IN THE BACKGROUND
////                while (buffer.readLine() != null) {
//                    val str = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
//                    lateinit var continent: Continent
//                    when (str[2]) {
//                        "Asia" -> continent = Continent("Asia", 44)
//                        "Europe" -> continent = Continent("Europe",50)
//                        "Australia" -> continent = Continent("Australia", 14)
//                        "South America" -> continent = Continent("SouthAmerica", 28)
//                        "North America" -> continent = Continent("NorthAmerica", 7)
//                        "Africa" -> continent = Continent("Africa", 54)
//                        else -> continue@loop
//                    }
//                    val country = Country(str[0], CapitalCity(str[1]), continent.continentName)
//                    countries.add(country)
//                    line = buffer.readLine()
//                }
//
//
//            } catch (e: FileNotFoundException) {
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            return countries
//        }
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
//
//    @TypeConverter
//    fun toContinent(stringified: String): Continent {
//        return Continent.createFromString(stringified)
//    }
//
//    @TypeConverter
//    fun fromContinent(c: Continent): String {
//        return c.stringify()
//    }
}
