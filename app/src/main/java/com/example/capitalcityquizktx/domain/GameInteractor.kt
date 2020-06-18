package com.example.capitalcityquizktx.domain

import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.data.CountryRepository
import com.example.capitalcityquizktx.data.models.geographical.Continent
import com.example.capitalcityquizktx.data.models.geographical.Country
import io.reactivex.Single

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
class GameInteractor(
    private val countryRepository: CountryRepository
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
        return countryRepository.getCountryList()
    }

    override fun getCountriesIn(continents: List<Continent>): List<Country> {
        return  getCountriesBy(continents).shuffled()
//        return Transformations.map(getCountriesBy(continents)){
//            it.shuffled(Random(System.currentTimeMillis()))
//        } as MutableLiveData<MutableList<Country>>
    }

    private fun getCountriesBy(continents :List<Continent>): List<Country>{
        return  countryRepository.getCountryListBy(continents)
    }

    override fun destroyCountries() {
        countryRepository.removeCountries()
    }

    override fun insertAllCountries(countries: List<Country>) {
        countryRepository.insertCountries(countries)
    }

    override fun getDataFieldsCount(): Int {
        return countryRepository.getFieldsCount()
    }

    override fun getCountriesFromStream(): List<Country> {
//        DatabaseUtils.getCountriesFromStream(applicationContext
//            .resources.openRawResource(R.raw.allcountries), ContinentSelector() )
         return countryRepository.getCountriesFromFile()
    }

//    override fun getLearnedCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun getLearningCountryListBy(user: User): LiveData<List<PracticeViewModel>> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}