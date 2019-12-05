package com.example.capitalcityquizktx.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.utils.ContinentSelector
import io.reactivex.Single

class DataDownloader(private val dataApi : DataApi) : DataRepository {
    override fun getCountryList(): Single<List<Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountryList(gameConfig: GameConfig): Single<List<Country>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
    