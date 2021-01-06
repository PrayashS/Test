package com.Prayash.week5assignment1.model

import android.os.Parcel
import android.os.Parcelable

data class StoryData(val userName:String?,val img:String?,val story:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(img)
        parcel.writeString(story)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StoryData> {
        override fun createFromParcel(parcel: Parcel): StoryData {
            return StoryData(parcel)
        }

        override fun newArray(size: Int): Array<StoryData?> {
            return arrayOfNulls(size)
        }
    }

}