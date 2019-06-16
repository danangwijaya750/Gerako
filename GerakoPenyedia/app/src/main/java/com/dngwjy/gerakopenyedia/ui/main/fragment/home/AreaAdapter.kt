package com.dngwjy.gerakopenyedia.ui.main.fragment.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.data.model.AreaResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.area_item.*

/**
 * Created by wijaya on 15/06/19
 */
class AreaAdapter(val data:List<AreaResponse.Area>, val listener:(AreaResponse.Area)->Unit)
    :RecyclerView.Adapter<AreaAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.area_item,p0,false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(data[p1],listener)
    }

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindData(area:AreaResponse.Area,listen:(AreaResponse.Area)->Unit){
            nameAre.text=area.name
            Glide.with(containerView).load(area.photo).into(imageArea)
            itemView.setOnClickListener { listen(area) }
        }
    }
}