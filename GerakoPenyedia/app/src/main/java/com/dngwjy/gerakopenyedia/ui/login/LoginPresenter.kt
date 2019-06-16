package com.dngwjy.gerakopenyedia.ui.login

import com.google.firebase.auth.FirebaseAuth

/**
 * Created by wijaya on 15/06/19
 */
class LoginPresenter(val view: LoginView,val fAuth: FirebaseAuth) {
    fun doLogin(email:String,password:String){
        view.isLoading(true)
        fAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    view.loginResult(true,email)
                    view.isLoading(false)
                }else{
                    view.loginResult(false,email)
                    view.isLoading(false)
                }
            }
    }
    fun doRegister(email:String,password:String){
        view.isLoading(true)
        fAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    uploadToBackEnd()
                }
            }
    }
    fun uploadToBackEnd(){
        view.isLoading(true)
        view.isLoading(false)
    }
}