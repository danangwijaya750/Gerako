package com.dngwjy.gerakopenyedia.ui.bookings

import com.dngwjy.gerakopenyedia.data.remote.ApiClient
import com.dngwjy.gerakopenyedia.utils.logD
import com.dngwjy.gerakopenyedia.utils.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 16/06/19
 */
class BookingPresenter (val view:BookingView){
    fun getBookings(param:String){
        view.isLoading(true)
        ApiClient.repository().getBookings(param).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view.isLoading(false)
                logD(it.bookingList.toString())
                view.showResult(it.bookingList)
            },{
                view.isLoading(false)
                logE(it.localizedMessage)
            })
    }
}