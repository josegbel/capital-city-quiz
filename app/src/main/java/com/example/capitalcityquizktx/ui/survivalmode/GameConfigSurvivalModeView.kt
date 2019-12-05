package com.example.capitalcityquizktx.ui.survivalmode

import androidx.lifecycle.MutableLiveData
import com.example.capitalcityquizktx.model.database.Continent

interface GameConfigSurvivalModeView {

    val continentsList: MutableLiveData<List<Continent>>

    fun showQuestionsNumberSelection()

    fun showTimeLimitSelection()

    fun hideQuestionsNumberSelection()

    fun hideTimeLimitSelection()
}
