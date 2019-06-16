package com.dngwjy.gerakopenyedia.ui.main.fragment.home

import com.dngwjy.gerakopenyedia.data.remote.ApiClient
import com.dngwjy.gerakopenyedia.utils.logD
import com.dngwjy.gerakopenyedia.utils.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 15/06/19
 */
class HomePrensenter(val view: HomeView) {
    fun getArea(place:String){
        view.isLoading(true)
        ApiClient.repository().getArea(place).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                logD(it.area.size.toString())
                view.isLoading(false)
                view.showResult(it.area)
            },{
                view.isLoading(false)
                logE(it.localizedMessage)
            })
    }
}