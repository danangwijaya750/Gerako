package com.dngwjy.gerakopenyedia.data.remote

import com.dngwjy.gerakopenyedia.data.model.AreaResponse
import com.dngwjy.gerakopenyedia.data.model.BookingResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by wijaya on 15/06/19
 */
interface ApiRepo {
    @GET("places/area")
    fun getArea(@Query("place")place:String): Observable<AreaResponse>
    @GET("places/books")
    fun getBookings(@Query("area_id")uid:String): Observable<BookingResponse>
}