package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.ui.survivalmode.GameConfigSurvivalView

/*

    J. Garcia CapitalCityQuiz in Kotlin 2019

 */
class GameConfigSurvivalPresenter(val view: GameConfigSurvivalView) {

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