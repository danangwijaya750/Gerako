package com.dngwjy.gerakopenyedia.ui.bookings

import com.dngwjy.gerakopenyedia.data.model.BookingResponse

/**
 * Created by wijaya on 16/06/19
 */
interface BookingView {
    fun isLoading(state:Boolean)
    fun showResult(data:List<BookingResponse.Booking>)
}