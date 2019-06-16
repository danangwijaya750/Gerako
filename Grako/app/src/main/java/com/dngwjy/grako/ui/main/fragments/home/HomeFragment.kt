package com.dngwjy.grako.ui.main.fragments.home

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.BannerModel
import com.dngwjy.grako.ui.main.MainActivity
import com.dngwjy.grako.ui.main.fragments.searching.SearchFragment
import com.dngwjy.grako.ui.main.fragments.searching.SearchMatchFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

/**
 * Created by wijaya on 15/06/19
 */
class HomeFragment:Fragment(){
lateinit var  handler:Handler
    lateinit var Update:Runnable
    var onFrag=true
    companion object {
        private var currentPage = 0
        private var NUM_PAGES = 3
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBanner()
        cardInit()
    }
    private fun cardInit(){
        futsal_card.setOnClickListener {
            showDialog("1")
        }
        basket_card.setOnClickListener {
            showDialog("basket")
        }
        volly_card.setOnClickListener {
            showDialog("volly")
        }
        sepakbola_card.setOnClickListener {
            showDialog("sepak")
        }
    }

    private fun showDialog(sport:String){
        val builder=AlertDialog.Builder(this.context)
        builder.setTitle("Pilih Mode")
        builder.setMessage("Silahkan pilih mode booking anda")
        SearchFragment.sport=sport
        builder.setPositiveButton("Latihan"){dialog, which ->
            SearchFragment.type="latihan"
            (activity as MainActivity).changeFragment(SearchFragment())
            (activity as MainActivity).isHome=false
        }
        builder.setNegativeButton("Tanding"){dialog, which ->
            SearchFragment.type="tanding"
            (activity as MainActivity).changeFragment(SearchMatchFragment())
            (activity as MainActivity).isHome=false
        }
        builder.setNeutralButton("Turnamen"){dialog, which ->
            SearchFragment.type="turnamen"
            (activity as MainActivity).changeFragment(SearchMatchFragment())
            (activity as MainActivity).isHome=false

        }
        val alert=builder.create()
        alert.show()
    }

    private fun setBanner(){
        val dataImage:MutableList<BannerModel> = mutableListOf()
        dataImage.add(BannerModel("banner_11"))
        dataImage.add(BannerModel("banner_2"))
        dataImage.add(BannerModel("banner_3"))
        pager.adapter=BannerAdapter(this.context!!,dataImage)
        indicator.setViewPager(pager)
        indicator.radius=5*resources.displayMetrics.density
         handler = Handler()
        Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            if(onFrag) {
                pager.setCurrentItem(currentPage++, true)
            }
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 2000, 2000)
        indicator.setOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

            }

            override fun onPageSelected(p0: Int) {
                currentPage = p0
            }

        })

    }

    override fun onDetach() {
        onFrag=false
        super.onDetach()

    }
    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}