package com.dngwjy.grako.ui.main

import android.Manifest
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDialogFragment
import com.dngwjy.grako.R
import com.dngwjy.grako.data.gps.GpsService
import com.dngwjy.grako.ui.main.fragments.home.HomeFragment
import com.dngwjy.grako.ui.main.fragments.profile.ProfileFragment
import com.dngwjy.grako.ui.main.fragments.rent.RentFragment
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.logE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
var isHome=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            1
        )
        navigation.setOnNavigationItemSelectedListener(navigateListener)
        changeFragment(HomeFragment())
    }
    val navigateListener=BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                changeFragment(HomeFragment())
                isHome = true
                return@OnNavigationItemSelectedListener true
            }
            R.id.sewa -> {
                changeFragment(RentFragment())
                isHome = false
                return@OnNavigationItemSelectedListener true
            }

            R.id.akun -> {
                changeFragment(ProfileFragment())
                isHome = false
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {

                if (grantResults.size > 0 && grantResults[0] === PackageManager.PERMISSION_GRANTED) {

                    getLocate()
                } else {
                    this.finish()
                }
                return
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(broadcastReceiver, IntentFilter(GpsService.str_reciver))
    }


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p1 != null) {
                if (p1.getStringExtra("latitude") != null && p1.getStringExtra("longitude") != null) {

                   /* if(mapsCommunicator!=null){
                        mapsCommunicator!!.sendLocation(p1.getStringExtra("latitude").toDouble(),p1.getStringExtra("longitude").toDouble())
                        logD("communicator attach")
                    } */


                }
            }else{
                logE("disable")
            }
        }
    }


    private fun getLocate() {
        if (serviceRunning(GpsService::class.java)) {
//            toast("Service not Running")
        } else {
//            toast("Service is Running")
            val intent = Intent(applicationContext, GpsService::class.java)
            startService(intent)
            registerReceiver(broadcastReceiver, IntentFilter(GpsService.str_reciver))
        }
    }

    private fun serviceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }


    override fun onBackPressed() {
        if(isHome==false){
            navigation.selectedItemId=R.id.home
        }else {
            super.onBackPressed()
        }
    }

    fun changeFragment(fragment: Fragment){
        val transact=supportFragmentManager.beginTransaction()
        transact.replace(R.id.frame_container,fragment)
        transact.commit()
    }
}
