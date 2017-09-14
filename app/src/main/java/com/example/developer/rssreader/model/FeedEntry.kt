package com.example.developer.rssreader.model

import android.os.Parcel
import android.os.Parcelable


class FeedEntry(val title: String?, val content: String?) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<FeedEntry> {
        override fun createFromParcel(parcel: Parcel): FeedEntry {
            return FeedEntry(parcel)
        }

        override fun newArray(size: Int): Array<FeedEntry?> {
            return arrayOfNulls(size)
        }
    }

}