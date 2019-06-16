package com.dngwjy.grako.ui.detail

import com.dngwjy.grako.data.remote.ApiClient
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 15/06/19
 */
class DetailPresenter (val view: DetailView){
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