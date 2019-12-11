package com.example.capitalcityquizktx.domain

import androidx.lifecycle.LiveData
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class GameInteractor(
    private val dataRepository: DataRepository
) : GameUseCases{
    override fun getAllCountries(): Single<List<Country>> {
        return dataRepository.getCountryList()
    }

    override fun getCountriesIn(continent: Continent): LiveData<MutableList<Country>> {
        return dataRepository.getCountryListBy(continent)
    }

    override fun destroyCountries() {
        dataRepository.removeCountries()
    }

    override fun insertAllCountries(countries: List<Country>) {
        dataRepository.insertCountries(countries)
    }

    override fun getDataFieldsCount(): Int {
        return dataRepository.getFieldsCount()
    }

    override fun getCountriesFromStream(): List<Country> {
//        DatabaseUtils.getCountriesFromStream(applicationContext
//            .resources.openRawResource(R.raw.allcountries), ContinentSelector() )
         return dataRepository.getCountriesFromFile()
    }

//    override fun getLearnedCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getLearningCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}