package com.example.capitalcityquizktx.config

import android.os.Parcelable
import com.example.capitalcityquizktx.model.database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
class SurvivalGameConfig (val continents: ArrayList<Continent>,
                          val numQuestions : Int,
                          val timeLimit: Int) : GameConfig, Parcelable