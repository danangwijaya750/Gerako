package com.dngwjy.grako.data.remote

import com.dngwjy.grako.data.model.AreaResponse
import com.dngwjy.grako.data.model.BookingResponse
import com.dngwjy.grako.data.model.PlaceResponse
import com.dngwjy.grako.data.model.PostResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by wijaya on 15/06/19
 */
interface ApiRepo {

    @GET("places/nearby")
    fun searchPlace(@Query("category") cat:String,
                    @Query("city")location:String?,
                    @Query("date") date:String?,
                    @Query("time") time:String?):Observable<PlaceResponse>
    @GET("places/area")
    fun getArea(@Query("place")place:String):Observable<AreaResponse>
    @GET("user/books")
    fun getBookings(@Query("uid")uid:String,@Query("status")status:String):Observable<BookingResponse>

    @POST("booking/practice")
    @FormUrlEncoded
    fun sendBooking(@Field("area_id")area_id:String,
                    @Field("uid")uid:String,
                    @Field("price")price:String,
                    @Field("date")date:String,
                    @Field("start_time")start_time:String,
                    @Field("end_time")end_time:String,
                    @Field("type")type:String):Observable<PostResponse>

    @POST("booking/status/update")
    @FormUrlEncoded
    fun confrimPayment(@Field("book")id:String,
                    @Field("status")status:String
                    ):Observable<PostResponse>
}