package com.example.capitalcityquizktx.ui.survivalmode

import androidx.lifecycle.ViewModel
import com.example.capitalcityquizktx.data.models.geographical.Country

abstract class SurvivalGameViewModel : ViewModel() {
    abstract fun getNextQuestion(list: MutableList<Country>) : Country?
    abstract fun checkAnswer(question: Country, answer: String): Boolean
}