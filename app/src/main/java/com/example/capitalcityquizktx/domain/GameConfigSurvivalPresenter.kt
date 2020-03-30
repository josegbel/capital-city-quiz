package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.ui.survivalmode.config.GameConfigSurvivalView

/**

    J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

    This presenter enables/disable view features on the GameConfigSurvivalFragment

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

    fun calculateRecommendedTimeLimit(countries : Int): Int {
        if (countries <= 0){
            throw IllegalArgumentException()
        }
        return 15 * countries
    }
}