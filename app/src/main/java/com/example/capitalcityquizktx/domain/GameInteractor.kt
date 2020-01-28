package com.example.capitalcityquizktx.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.capitalcityquizktx.model.DataRepository
import com.example.capitalcityquizktx.model.database.Continent
import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single
import kotlin.random.Random

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class GameInteractor(
    private val dataRepository: DataRepository
) : GameUseCases{

    override fun getNextQuestion(list: MutableList<Country>): MutableLiveData<Country>? {
        val country = MutableLiveData<Country>()
        if (list.isEmpty()) {
            return null
        }
        country.postValue(list[0])
        list.removeAt(0)
        return country
    }

    override fun shuffleList(list: MutableList<Country>) {
    }

    override fun getAllCountries(): Single<List<Country>> {
        return dataRepository.getCountryList()
    }

    override fun getCountriesIn(continents: List<Continent>): MutableLiveData<MutableList<Country>> {

//        countries.let {
//            it.addAll(getCountriesBy(continents))
//            shuffle(it)
//        }
        return Transformations.map(getCountriesBy(continents)){
            it.shuffled(Random(System.currentTimeMillis()))
        } as MutableLiveData<MutableList<Country>>
    }

    private fun getCountriesBy(continents :List<Continent>): MutableLiveData<MutableList<Country>>{
        return  dataRepository.getCountryListBy(continents)
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