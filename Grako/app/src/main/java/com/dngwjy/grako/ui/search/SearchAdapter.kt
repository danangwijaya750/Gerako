package com.dngwjy.grako.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.PlaceResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.search_item.*

/**
 * Created by wijaya on 15/06/19
 */
class SearchAdapter(val data:List<PlaceResponse.Place>,val listener:(PlaceResponse.Place)->Unit)
    :RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.search_item,p0,false))
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(data[p1],listener)
    }

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindData(place:PlaceResponse.Place,listen:(PlaceResponse.Place)->Unit){
            namaTempat.text=place.name
            alamatTempat.text=place.address
            Glide.with(containerView).load(place.photo).into(imageTempat)
            itemView.setOnClickListener{listen(place)}
        }
    }
}