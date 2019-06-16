package com.dngwjy.grako.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by wijaya on 15/06/19
 */
data class AreaResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("results")
    val area:List<Area>
) {
    @Parcelize
    data class Area(
        @SerializedName("id")
        val id:Int,
        @SerializedName("name")
        val name:String,
        @SerializedName("day_price")
        val dayPrice:String,
        @SerializedName("night_price")
        val nightPrice:String,
        @SerializedName("photo")
        val photo:String,
        @SerializedName("category_id")
        val category: Int
    ):Parcelable

}