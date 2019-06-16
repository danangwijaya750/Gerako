package com.dngwjy.grako.data.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by wijaya on 15/06/19
 */
class ApiClient {
    companion object{
        fun builder():Retrofit.Builder{
            return Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).baseUrl("http://10.232.73.1:8000/")
        }

        fun retrofitBuild():Retrofit{
            return builder().build()
        }

        fun repository():ApiRepo{
            return  retrofitBuild().create(ApiRepo::class.java)
        }
    }
}