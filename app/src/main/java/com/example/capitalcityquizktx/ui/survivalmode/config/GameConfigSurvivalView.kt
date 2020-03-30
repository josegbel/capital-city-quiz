package com.example.capitalcityquizktx.ui.survivalmode.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.model.database.Continent

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface GameConfigSurvivalView {

    val continentsList : MutableLiveData<List<Continent>>

    val numberOfCountries : LiveData<Int>

    fun showQuestionsNumberSelection()

    fun showTimeLimitSelection()

    fun hideQuestionsNumberSelection()

    fun hideTimeLimitSelection()
}
