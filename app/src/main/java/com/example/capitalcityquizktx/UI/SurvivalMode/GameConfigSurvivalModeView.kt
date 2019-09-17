package com.example.capitalcityquizktx.UI.SurvivalMode

import com.example.capitalcityquizktx.Database.Continent

interface GameConfigSurvivalModeView {

    fun getContinentSelection(): List<Continent>

    fun showQuestionsNumberSelection()

    fun showTimesetSelection()

    fun hideQuestionsNumberSelection()

    fun hideTimesetSelection()
}
