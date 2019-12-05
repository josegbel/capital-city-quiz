package com.example.capitalcityquizktx.utils

import com.example.capitalcityquizktx.model.database.CapitalCity
import com.example.capitalcityquizktx.model.database.Country
import java.io.*
import kotlin.collections.emptyList as emptyList1

class DatabaseUtils{

    companion object {

        fun getCountriesFromStream(file: InputStream,
                                   continentSelector : ContinentSelector): List<Country> {
            val countries : MutableList<Country> = mutableListOf()
                try {
                    val reader = InputStreamReader(file)
                    val buffer = BufferedReader(reader)
                    var line: String?
                    line = buffer.readLine()
                    loop@ while (line != null) {
                        val str = line
                            .split(",".toRegex())
                            .dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        countries.add(Country(str[0],
                                              CapitalCity(str[1]),
                                              continentSelector.getContinent(str[2])))
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
    }
}