package com.example.capitalcityquizktx.domain

import com.example.capitalcityquizktx.model.database.Country
import io.reactivex.Single

interface QuestionsPresenter{
    val countryList : Single<List<Country>>

    fun shuffleQuestions()
    fun getNextQuestion() : Country
    fun onCorrectAnswer()
    fun onWrongAnswer()
}