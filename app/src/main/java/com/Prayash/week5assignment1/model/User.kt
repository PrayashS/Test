package com.Prayash.week5assignment1.model

import android.os.Parcel
import android.os.Parcelable


data class User(val fname:String?,val lname:String?,val username:String?,val password:String?,val batch:String?,val batchId:String?,val profileURL:String?):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeString(batch)
        parcel.writeString(batchId)
        parcel.writeString(profileURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}