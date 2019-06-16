package com.dngwjy.grako.ui.booking

import com.dngwjy.grako.data.model.BookingResponse
import com.dngwjy.grako.data.model.PostResponse

/**
 * Created by wijaya on 15/06/19
 */
interface BookingView {
    fun isLoading(state:Boolean)
    fun showResult(state: Boolean)
}