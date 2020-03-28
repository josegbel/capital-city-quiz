package com.example.capitalcityquizktx.config

import android.os.Parcelable
import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

/**

J. Garcia CapitalCityQuiz in Kotlin 10/12/2019

 */
@Parcelize
open class SurvivalGameConfig (val continents: ArrayList<Continent>,
                          val numQuestions : Int,
                          val timeLimit: Int) : GameConfig, Parcelable