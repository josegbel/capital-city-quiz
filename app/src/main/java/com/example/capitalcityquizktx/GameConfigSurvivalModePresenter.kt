package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.ui.survivalmode.GameConfigSurvivalModeView

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class GameConfigSurvivalModePresenter(val view: GameConfigSurvivalModeView) {

    fun receiveContinentSelection() {
        view.continentsList.observeForever { list ->
            if (list.isEmpty()) {
                view.hideQuestionsNumberSelection()
                view.hideTimeLimitSelection()
            } else {
                view.showQuestionsNumberSelection()
                view.showTimeLimitSelection()
            }
        }
    }
}