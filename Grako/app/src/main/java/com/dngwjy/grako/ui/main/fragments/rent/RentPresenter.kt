package com.dngwjy.grako.ui.main.fragments.rent

import com.dngwjy.grako.data.remote.ApiClient
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.logE
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 15/06/19
 */
class RentPresenter(val view: RentView,val fAuth:FirebaseAuth) {
    fun getBookings(param:String){
        view.isLoading(true)
        ApiClient.repository().getBookings(fAuth.currentUser!!.uid,param).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                view.isLoading(false)
                    view.showResult(it.bookingList)
            },{
                view.isLoading(false)
                logE(it.localizedMessage)
            })
    }
}