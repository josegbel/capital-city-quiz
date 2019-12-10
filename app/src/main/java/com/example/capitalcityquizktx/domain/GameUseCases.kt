package com.example.capitalcityquizktx.domain

import androidx.lifecycle.LiveData
import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.model.database.User
import io.reactivex.Single

interface GameUseCases {
//    fun getLearnedCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
//    fun getLearningCountryListBy(user: User) : LiveData<List<PracticeViewModel>>
    fun getCountryListBy(gameConfig : GameConfig) : Single<List<Country>>
    fun destroyCountries()
    fun insertAllCountries(countries: List<Country>)
    fun getDataFieldsCount(): Int
    fun getCountriesFromStream() : List<Country>
}