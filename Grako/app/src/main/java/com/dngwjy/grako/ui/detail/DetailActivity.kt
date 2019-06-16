package com.dngwjy.grako.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.AreaResponse
import com.dngwjy.grako.data.model.PlaceResponse
import com.dngwjy.grako.ui.booking.BookingActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),DetailView {
    override fun isLoading(state: Boolean) {
        swipper.isRefreshing=state
    }

    override fun showResult(data: List<AreaResponse.Area>) {
        dataArea.clear()
        dataArea.addAll(data)
        adapter.notifyDataSetChanged()
    }
    lateinit var presenter:DetailPresenter
    lateinit var dataPlace:PlaceResponse.Place
    lateinit var adapter: AreaAdapter

    val dataArea:MutableList<AreaResponse.Area> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
        dataPlace=intent.extras.getParcelable("data")
        presenter= DetailPresenter(this)
        Glide.with(this).load(dataPlace.photo).into(imagePlace)
        namaTempat.text=dataPlace.name
        alamatTempat.text=dataPlace.address
        presenter.getArea(dataPlace.id.toString())
        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView2.layoutManager=layoutManager
        adapter= AreaAdapter(dataArea){
            val intent=Intent(this,BookingActivity::class.java)
            intent.putExtra("data1",dataPlace)
            intent.putExtra("data2",it)
            startActivity(intent)

        }
        recyclerView2.adapter=adapter
        swipper.setOnRefreshListener {
            presenter.getArea(dataPlace.id.toString())
        }
    }
}
