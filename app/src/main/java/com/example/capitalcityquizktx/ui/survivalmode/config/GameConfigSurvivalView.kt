package com.example.capitalcityquizktx.ui.survivalmode.config

import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.model.database.Continent

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
interface GameConfigSurvivalView {

    val continentsList: MutableLiveData<List<Continent>>

    fun showQuestionsNumberSelection()

    fun showTimeLimitSelection()

    fun hideQuestionsNumberSelection()

    fun hideTimeLimitSelection()
}
