package com.example.capitalcityquizktx.model

import com.example.capitalcityquizktx.model.database.CapitalCity
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.utils.ContinentSelector
import io.reactivex.Observable
import io.reactivex.Single
import java.io.*

class DataApi {
    fun getCountryList(file: InputStream,
                               continentSelector : ContinentSelector
    ): Single<List<Country>> {
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
                countries.add(
                    Country(str[0],
                        CapitalCity(str[1]),
                        continentSelector.getContinent(str[2]))
                )
                line = buffer.readLine()
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (countries.equals(emptyList<Country>()))
            throw RuntimeException("Country file is empty")

        return Single.just(countries)
    }
}
