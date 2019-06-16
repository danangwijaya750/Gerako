package com.dngwjy.gerakopenyedia.ui.bookings

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import com.dngwjy.gerakopenyedia.R
import com.dngwjy.gerakopenyedia.data.model.AreaResponse
import com.dngwjy.gerakopenyedia.data.model.BookingResponse
import com.dngwjy.gerakopenyedia.utils.logD
import kotlinx.android.synthetic.main.activity_daftar_booking.*

class DaftarBooking : AppCompatActivity(),BookingView {
    override fun isLoading(state: Boolean) {
        swipe.isRefreshing=state
    }

    override fun showResult(data: List<BookingResponse.Booking>) {
        dataBooking.clear()
        dataBooking.addAll(data)
        adapter.notifyDataSetChanged()
    }

    val dataBooking:MutableList<BookingResponse.Booking> = mutableListOf()
    lateinit var presenter: BookingPresenter
    lateinit var adapter: BookingsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_booking)
        supportActionBar?.hide()
        presenter= BookingPresenter(this)
        adapter= BookingsAdapter(dataBooking){
//            val intent= Intent(this,BookingDetail::class.java)
//            intent.putExtra("data",it)
//            startActivity(intent)
        }
        val layoutManager= LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        rvBook.layoutManager=layoutManager
        rvBook.adapter=adapter
        val data=intent.getParcelableExtra<AreaResponse.Area>("data")

        presenter.getBookings(data.id.toString())

        swipe.setOnRefreshListener {
            presenter.getBookings(data.id.toString())
        }
    }
}
