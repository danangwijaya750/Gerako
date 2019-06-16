package com.dngwjy.gerakopenyedia.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 15/06/19
 */
data class BookingResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("results")
    val bookingList:List<Booking>
) {
    @Parcelize
    data class Booking(
    @SerializedName("area_id")
    val areaId: Int?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("end_time")
    val endTime: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("photo")
    val photo: String?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("start_time")
    val startTime: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uid")
    val uid: String?
    ):Parcelable
}