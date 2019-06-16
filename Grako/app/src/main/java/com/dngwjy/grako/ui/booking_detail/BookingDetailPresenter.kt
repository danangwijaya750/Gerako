package com.dngwjy.grako.ui.booking_detail

import android.net.Uri
import com.dngwjy.grako.data.remote.ApiClient
import com.dngwjy.grako.utils.logE
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 16/06/19
 */
class BookingDetailPresenter(val view: BookingDetailView,val fAuth:FirebaseAuth,val storageReference: StorageReference) {
    fun uploadPayment(orderId:String, uriFile: Uri, fileType:String){
        view.isLoading(true)
                ApiClient.repository().confrimPayment(orderId,"payment")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        if(it.message.contains("berhasil")){
                            view.isLoading(false)
                            view.showResult(true)
                        }else{
                            view.isLoading(false)
                            view.showResult(false)
                        }
                    },{
                        logE(it.localizedMessage)
                        view.isLoading(false)
                        view.showResult(false)
                    })
        }
    }
