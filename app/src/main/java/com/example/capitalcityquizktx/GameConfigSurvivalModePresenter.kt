package com.example.capitalcityquizktx

import com.example.capitalcityquizktx.UI.SurvivalMode.GameConfigSurvivalModeView
import java.util.*

class GameConfigSurvivalModePresenter(val view: GameConfigSurvivalModeView){

    fun receiveContinentSelection() {

        if(view.getContinentSelection() == Collections.EMPTY_LIST){
            view.hideQuestionsNumberSelection()
            view.hideTimesetSelection()
        }else{
            view.showQuestionsNumberSelection()
            view.showTimesetSelection()
        }
    }

}