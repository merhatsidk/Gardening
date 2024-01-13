package com.example.gardening

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_table")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "plant_id")
    val plantId: Int = 0,

    @ColumnInfo(name = "plant_name")
    val plantName: String,

    @ColumnInfo(name = "species")
    val species: String,

    @ColumnInfo(name = "watering_schedule")
    val wateringSchedule: String,

    @ColumnInfo(name = "sunlight_requirement")
    val sunlightRequirement: String
) :  Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(plantId)
        parcel.writeString(plantName)
        parcel.writeString(species)
        parcel.writeString(wateringSchedule)
        parcel.writeString(sunlightRequirement)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plant> {
        override fun createFromParcel(parcel: Parcel): Plant {
            return Plant(parcel)
        }

        override fun newArray(size: Int): Array<Plant?> {
            return arrayOfNulls(size)
        }
    }
}
