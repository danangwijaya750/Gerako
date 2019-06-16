package com.dngwjy.grako.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wijaya on 15/06/19
 */
data class PostResponse(
    @SerializedName("status")
    val status:Int,
    @SerializedName("message")
    val message:String
) {
}