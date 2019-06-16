package com.dngwjy.gerakopenyedia.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.ui.main.fragment.home.HomeFragment
import com.dngwjy.gerakopenyedia.ui.main.fragment.profile.ProfileFragment
import com.dngwjy.gerakopenyedia.ui.main.fragment.rent.RentFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        navigation.setOnNavigationItemSelectedListener (navigateListener)
        changeFragment(HomeFragment())
    }

    fun changeFragment(fragment: Fragment){
        val transact=supportFragmentManager.beginTransaction()
        transact.replace(R.id.frame_container,fragment)
        transact.commit()
    }
    val navigateListener= BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                changeFragment(HomeFragment())
//                isHome = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.sewa -> {
                changeFragment(RentFragment())
//                isHome = false
                return@OnNavigationItemSelectedListener true
            }

            R.id.akun -> {
                changeFragment(ProfileFragment())
//                isHome = false
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
