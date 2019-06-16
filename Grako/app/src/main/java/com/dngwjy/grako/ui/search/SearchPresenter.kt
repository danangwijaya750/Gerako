package com.dngwjy.grako.ui.search

import android.util.Log
import com.dngwjy.grako.data.model.PlaceResponse
import com.dngwjy.grako.data.remote.ApiClient
import com.dngwjy.grako.data.remote.ApiRepo
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.logE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by wijaya on 16/06/19
 */
class SearchPresenter(private val view: SearchView) {
    fun getData(cat:String,location:String,date:String,time:String){
        view.isLoading(true)
        ApiClient.repository().searchPlace(cat, location, date, time).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d(this::class.java.simpleName,it.places.size.toString())
                if(it.places.size>0) {
                    view.showData(it.places)
                }
                view.isLoading(false)
            },{
                Log.e(this::class.java.simpleName,it.localizedMessage)
                view.isLoading(false)
            })
    }
}