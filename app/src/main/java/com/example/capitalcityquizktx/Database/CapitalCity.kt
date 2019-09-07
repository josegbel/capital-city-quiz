package com.example.capitalcityquizktx.Database

import android.os.Parcel
import android.os.Parcelable

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
    }
}