package com.dngwjy.grako.ui.main.fragments.rent

import com.dngwjy.grako.data.model.BookingResponse

/**
 * Created by wijaya on 15/06/19
 */
interface RentView {
    fun isLoading(state:Boolean)
    fun showResult(data:List<BookingResponse.Booking>)
}