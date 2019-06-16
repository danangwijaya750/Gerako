package com.dngwjy.grako.ui.main.fragments.rent

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.BookingResponse
import com.dngwjy.grako.ui.booking_detail.BookingDetail
import com.dngwjy.grako.utils.logD
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_rent.*

/**
 * Created by wijaya on 15/06/19
 */
class RentFragment: Fragment(),RentView {
    val dataBooking:MutableList<BookingResponse.Booking> = mutableListOf()
    lateinit var presenter: RentPresenter
    lateinit var adapter: BookingsAdapter
    override fun isLoading(state: Boolean) {
        swipe.isRefreshing=state
    }

    override fun showResult(data: List<BookingResponse.Booking>) {
        dataBooking.clear()
        dataBooking.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rent,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fAuth=FirebaseAuth.getInstance()
        presenter=RentPresenter(this,fAuth)
        adapter= BookingsAdapter(dataBooking){
            val intent= Intent(this.context,BookingDetail::class.java)
            intent.putExtra("data",it)
            startActivity(intent)
        }
        val layoutManager=LinearLayoutManager(this.context)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        rvBook.layoutManager=layoutManager
        rvBook.adapter=adapter

        getData()
        spinnerType.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getData()
                logD(spinnerType.selectedItem.toString())
            }

        }
        swipe.setOnRefreshListener {
            getData()
        }

    }
    private fun getData(){
        val status:String=when(spinnerType.selectedItemPosition){
            0->"null"
            1->"payment"
            2->"verified"
            else->"finished"
        }
        presenter.getBookings(status)
        logD(spinnerType.selectedItem.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}