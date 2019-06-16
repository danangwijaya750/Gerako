package com.dngwjy.grako.ui.main.fragments.home

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.BannerModel
import kotlinx.android.synthetic.main.banner_layout.banner_image

/**
 * Created by wijaya on 14/06/19
 */
class BannerAdapter(val context:Context,val data:List<BannerModel>):PagerAdapter() {
    private val layoutInflater:LayoutInflater
    init {
        layoutInflater= LayoutInflater.from(context)
    }
    private fun getRes(name:String):Int{
        val draw=context.resources.getIdentifier(name,"drawable",context.packageName)
        return draw
    }
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val bannerLayout=layoutInflater.inflate(R.layout.banner_layout,container,false)
        val image=bannerLayout.findViewById<ImageView>(R.id.banner_image)
        Glide.with(container).load(getRes(data[position].imageBanner)).into(image)
        container.addView(bannerLayout,0)
        return bannerLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0==p1
    }

    override fun getCount(): Int =data.size
}