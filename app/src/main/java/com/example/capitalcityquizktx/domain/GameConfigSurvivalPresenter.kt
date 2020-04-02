package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.ui.survivalmode.config.GameConfigSurvivalView
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

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

    @ExperimentalTime
    fun formatTime(millis: Long) : String? {

        val seconds = kotlin.time.Duration.convert(
            millis.toDouble(),
            DurationUnit.MILLISECONDS,
            DurationUnit.SECONDS
        )

        val minutes = kotlin.time.Duration.convert(
            millis.toDouble(),
            DurationUnit.MILLISECONDS,
            DurationUnit.MINUTES
        )

        val hours = kotlin.time.Duration.convert(
            millis.toDouble(),
            DurationUnit.MILLISECONDS,
            DurationUnit.HOURS
        )

        var minutesRemaining = minutes % 60

        val secondsRemaining = seconds % 60

        var timeStr: String? = ""

        if(minutes >= 60){
            timeStr +=
                if(hours < 10)
                    "0%d:".format(hours.toInt())
                else
                    "%d:".format(hours.toInt())

            minutesRemaining = minutes % 60
        }

        if (minutesRemaining > 0) {

            timeStr +=
                if (minutesRemaining < 10)
                    "0%d".format(minutesRemaining.toInt())
                else
                    "%d".format(minutesRemaining.toInt())

            timeStr +=
                if (secondsRemaining < 10)
                    ":0%d".format(secondsRemaining.toInt())
                else
                    ":%d".format(secondsRemaining.toInt())

        } else
            timeStr += "00:00"

        return timeStr
    }
}