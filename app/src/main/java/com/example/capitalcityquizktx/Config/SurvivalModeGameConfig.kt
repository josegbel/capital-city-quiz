package com.example.capitalcityquizktx.Config

import android.os.Parcelable
import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
class SurvivalModeGameConfig (val continents: ArrayList<Continent>,
                              val numQuestions : Int,
                              val timeLimit: Int) : GameConfig(), Parcelable