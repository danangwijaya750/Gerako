package com.dngwjy.grako.ui.search

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.PlaceResponse
import com.dngwjy.grako.ui.detail.DetailActivity
import com.dngwjy.grako.utils.logD
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity(),SearchView {
    val dataSearc:MutableList<PlaceResponse.Place> = mutableListOf()
    lateinit var adapter: SearchAdapter

    var loc=""
    var sport=""
    var date=""
    var time=""

    override fun isLoading(state: Boolean) {
        swipper.isRefreshing=state
    }

    override fun showData(data: List<PlaceResponse.Place>) {
        logD(data.size.toString())
        dataSearc.clear()
        dataSearc.addAll(data)
        adapter.notifyDataSetChanged()
    }

    lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)
        supportActionBar?.hide()
        loc=intent.getStringExtra("loc")
        sport=intent.getStringExtra("sport")
        date=intent.getStringExtra("date")
        time=intent.getStringExtra("time")

        presenter=SearchPresenter(this)
        doSearching()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        recyclerView.layoutManager=layoutManager
        adapter= SearchAdapter(dataSearc){
            val intent=Intent(this,DetailActivity::class.java)
            intent.putExtra("data",it)
            intent.putExtra("date",date)
            startActivity(intent)
        }
        recyclerView.adapter=adapter
        swipper.setOnRefreshListener {
            doSearching()
        }

    }
    private fun doSearching(){
        presenter.getData(sport,loc,
            "","")
    }
}
