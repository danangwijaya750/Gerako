package com.dngwjy.grako.data.shared

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by wijaya on 12/06/19
 */
class SharePref(context:Context) {
    companion object{
        val PREF_NAME="com.dngwjy.grako.pref"
        val SPORTS_INTEREST="interest"
    }
    val pref:SharedPreferences=context.getSharedPreferences(SharePref.PREF_NAME,0)
    var sportInsterest:MutableSet<String>?
    get() = pref.getStringSet(SPORTS_INTEREST,null)
    set(value)=pref.edit().putStringSet(SPORTS_INTEREST,value).apply()

}