package com.example.capitalcityquizktx.model

import androidx.lifecycle.LiveData
import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

interface DataRepository {

    fun getCountryList() : Single<List<Country>>
    fun getCountryList(gameConfig: GameConfig) : Single<List<Country>>
    fun refreshCountries(){}


}
