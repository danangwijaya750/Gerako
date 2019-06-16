package com.dngwjy.grako.ui.booking

import com.dngwjy.grako.data.remote.ApiClient
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.logE
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 15/06/19
 */
class BookingPresenter(val view: BookingView,val firebaseAuth: FirebaseAuth) {
    fun doBooking(areaid:String,price:String,date:String,start_time:String,end_time:String,type:String){
        ApiClient.repository().sendBooking(areaid, firebaseAuth.currentUser!!.uid,
            price,date,start_time,end_time,type).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if(it.message=="Booking berhasil"){
                    view.isLoading(false)
                    view.showResult(true)
                }else{
                    view.isLoading(false)
                    view.showResult(true)
                }
            },{
                view.isLoading(false)
                view.showResult(true)
                logE(it.localizedMessage)
            })
    }


}