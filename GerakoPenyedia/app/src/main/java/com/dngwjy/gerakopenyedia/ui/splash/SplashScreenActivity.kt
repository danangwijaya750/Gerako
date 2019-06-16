package com.dngwjy.gerakopenyedia.ui.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_ACTION_BAR)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        val runnable= Runnable {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        Handler().postDelayed(runnable,2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}
