package com.dngwjy.grako.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 14/06/19
 */

data class PlaceResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("results")
    val places:List<Place>
) {
@Parcelize
data class Place(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("address")
    val address:String,
    @SerializedName("photo")
    val photo:String,
    @SerializedName("long")
    val longitude:Double,
    @SerializedName("lat")
    val latitude:Double
):Parcelable
}
