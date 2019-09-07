package com.example.capitalcityquizktx.Config

import android.os.Parcel
import android.os.Parcelable
import com.example.capitalcityquizktx.Database.Continent
import kotlinx.android.parcel.Parcelize

@Parcelize
class SurvivalModeGameConfig (val continents: ArrayList<Continent>,
                              val numQuestions : Int,
                              val timeLimit: Int) : GameConfig(), Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readArrayList()),
//        parcel.readInt(),
//        parcel.readInt()
//    ) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeTypedList(continents)
//        parcel.writeInt(numQuestions)
//        parcel.writeInt(timeLimit)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<SurvivalModeGameConfig> {
//        override fun createFromParcel(parcel: Parcel): SurvivalModeGameConfig {
//            return SurvivalModeGameConfig(parcel)
//        }
//
//        override fun newArray(size: Int): Array<SurvivalModeGameConfig?> {
//            return arrayOfNulls(size)
//        }
//    }
}