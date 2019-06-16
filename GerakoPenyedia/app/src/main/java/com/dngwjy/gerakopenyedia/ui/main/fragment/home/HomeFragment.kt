package com.dngwjy.gerakopenyedia.ui.main.fragment.home

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.data.model.AreaResponse
import com.dngwjy.gerakopenyedia.ui.bookings.DaftarBooking

import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by wijaya on 15/06/19
 */
class HomeFragment:Fragment(),HomeView{
    override fun showResult(data: List<AreaResponse.Area>) {
        dataArea.clear()
        dataArea.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun isLoading(state: Boolean) {

    }

    lateinit var presenter:HomePrensenter
    val dataArea:MutableList<AreaResponse.Area> = mutableListOf()
    lateinit var adapter: AreaAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter= HomePrensenter(this)
        presenter.getArea("1")
        val layoutManager= LinearLayoutManager(this.context)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager
        adapter= AreaAdapter(dataArea){
            val intent= Intent(this.context,DaftarBooking::class.java)
            intent.putExtra("data",it)
            startActivity(intent)

        }
        recyclerView.adapter=adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}