package com.example.capitalcityquizktx.model

import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface DataRepository {
    fun getCountryList() : Single<List<Country>>
    fun getCountryList(gameConfig: GameConfig) : Single<List<Country>>
    fun getFieldsCount() : Int
}
