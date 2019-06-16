package com.dngwjy.gerakopenyedia.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

/**
 * Created by wijaya on 14/06/19
 */
fun Context.toastBtm(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
fun Context.toastCnt(msg: String){
    val toast=Toast.makeText(this,msg,Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
}