package com.dngwjy.grako.ui.main.fragments.rent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.BookingResponse
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_booking.*

/**
 * Created by wijaya on 15/06/19
 */
class BookingsAdapter(val data:List<BookingResponse.Booking>,val listener:(BookingResponse.Booking)->Unit)
    :RecyclerView.Adapter<BookingsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.item_booking,p0,false))
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(data[p1],listener)
    }

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        fun bindData(booking:BookingResponse.Booking,listen: (BookingResponse.Booking) -> Unit){
            tanggalBook.text="Tanggal : ${booking.date}"
            timeBook.text="Waktu : ${booking.startTime} - ${booking.endTime}"
            val tipe:String=when(booking.type){
                "practice"->"Latihan"
                "match"->"Tanding"
                "turnamen"->"Turnamen"
                else->""
            }
            typeBook.text="Tipe : ${tipe}"
            price.text="Rp ${booking.price}"
            itemView.setOnClickListener { listen(booking) }
        }
    }
}