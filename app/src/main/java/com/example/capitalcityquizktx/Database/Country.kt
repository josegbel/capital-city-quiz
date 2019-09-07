package com.example.capitalcityquizktx.Database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class Country(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "countryName")
    var countryName: String?,

    @ColumnInfo(name = "capitalName")
    var capitalName: CapitalCity,

    @ColumnInfo(name = "continent")
    var continent: Continent?
) : Parcelable {

    // TODO IMPORTANT ; I AM passing getSystemClassLoader !!! instead of getClass().getClassLoader()
    private constructor(incoming: Parcel) : this(
        incoming.readString(),
        incoming.readParcelable(ClassLoader.getSystemClassLoader()),
        incoming.readParcelable(ClassLoader.getSystemClassLoader())
    )

    override fun describeContents(): Int {
        return 0
    }


    // TODO IMPORTANT I AM PASSING flags as flags
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.countryName)
        dest.writeParcelable(this.capitalName, flags)
        dest.writeParcelable(this.continent, flags)
    }

    companion object CREATOR : Parcelable.Creator<Country> {
        override fun createFromParcel(incoming: Parcel): Country {
            return Country(incoming)
        }

        override fun newArray(size: Int): Array<Country?> {
            return arrayOfNulls(size)
        }
    }
}

