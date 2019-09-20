package com.example.capitalcityquizktx.Database

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson

class CapitalCity(name: String, picture: String? = null) : City(name, picture), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest!!.writeString(name)
        dest.writeString(picture)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CapitalCity> {
        override fun createFromParcel(parcel: Parcel): CapitalCity {
            return CapitalCity(parcel)
        }

        override fun newArray(size: Int): Array<CapitalCity?> {
            return arrayOfNulls(size)
        }

        fun createFromString(string: String): CapitalCity {
            return Gson().fromJson(string, CapitalCity::class.java!!)
        }
    }

    fun stringify(): String {
        return Gson().toJson(this, CapitalCity::class.java!!)
    }
}