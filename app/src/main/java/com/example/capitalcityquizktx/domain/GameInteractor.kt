package com.example.capitalcityquizktx.domain

import android.app.Application
import android.content.Context
import com.example.capitalcityquizktx.R
import com.example.capitalcityquizktx.config.GameConfig
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.model.database.Country
import com.example.capitalcityquizktx.utils.ContinentSelector
import com.example.capitalcityquizktx.utils.DatabaseUtils
import io.reactivex.Single

class GameInteractor (private val dataRepository: DataRepository,
                      private val applicationContext : Application
) : GameUseCases{
    override fun getCountryListBy(gameConfig: GameConfig): Single<List<Country>> {
        return dataRepository.getCountryList(gameConfig)
    }

    override fun destroyCountries() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertAllCountries(countries: List<Country>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDataFieldsCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCountriesFromStream(): List<Country> {
        DatabaseUtils.getCountriesFromStream(applicationContext
            .resources.openRawResource(R.raw.allcountries), ContinentSelector()
        )
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun getLearnedCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getLearningCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}