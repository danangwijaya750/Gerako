package com.dngwjy.grako.ui.login

/**
 * Created by wijaya on 15/06/19
 */
interface LoginView {
    fun isLoading(state:Boolean)
    fun loginResult(state:Boolean,email:String)
}