package com.dngwjy.gerakopenyedia.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.ui.main.MainActivity
import com.dngwjy.gerakopenyedia.utils.toastCnt
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),LoginView {
    lateinit var loginPresenter: LoginPresenter
    override fun isLoading(state: Boolean) {
        when(state){
            true->{
                login_progress.visibility= View.VISIBLE
            }
            else->{
                login_progress.visibility= View.GONE
            }
        }
    }

    override fun loginResult(state: Boolean, email: String) {
        when(state){
            true->{
                toastCnt("wellcome")
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            else->{
                toastCnt("Failed")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        val fAuth= FirebaseAuth.getInstance()
        if(fAuth.currentUser!=null){
            toastCnt("wellcome")
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        loginPresenter=LoginPresenter(this,fAuth)
        email_sign_in_button.setOnClickListener {
            loginPresenter.doLogin(email.text.toString(),password.text.toString())
        }
    }

}
