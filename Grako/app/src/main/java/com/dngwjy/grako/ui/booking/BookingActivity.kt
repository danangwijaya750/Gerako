package com.dngwjy.grako.ui.booking

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.AreaResponse
import com.dngwjy.grako.data.model.BookingResponse
import com.dngwjy.grako.data.model.PlaceResponse
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.toastCnt
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_booking.*
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity(),BookingView {
    override fun isLoading(state: Boolean) {
        if(state){
            toastCnt("Mengirim data Bookingan Anda.")
        }
    }

    override fun showResult(state: Boolean) {
        when(state){
            true->{toastCnt("Booking berhasil,\n Silahkan lakukan proses pembayaran dan verifikasi pembayaran")
                onBackPressed()
            }
            false->{toastCnt("Booking gagal, silahkan tunggu beberapa saat lagi")}
        }
    }

    private var priceCounted:Int=0
    private var endTime:String=""
    private var hur:Int=0
    private var minut:Int=0
    lateinit var presenter: BookingPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        supportActionBar?.hide()
        val fAuth=FirebaseAuth.getInstance()
        presenter=BookingPresenter(this,fAuth)
        dateText.setOnClickListener {
            DatePickerDialog(this,dateDialog,calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        timeTextStart.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    hur=selectedHour
                    minut=selectedMinute
                    timeTextStart.setText(
                        "$selectedHour:$selectedMinute:00"
                    )
                }, hour, minute, true
            )//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }
        timeDurasi.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(!s.isNullOrEmpty()){
                    countPrice()
                }
            }

        })

        buttonCari.setOnClickListener {
            if(timeDurasi.text.toString()==""||timeTextStart.text.toString()==""||priceText.text.toString()==""){
                toastCnt("Semua Data Harus Diisi!")
            }else{
                val data1=intent.getParcelableExtra<PlaceResponse.Place>("data1")
                val data2=intent.getParcelableExtra<AreaResponse.Area>("data2")
                presenter.doBooking(data2.id.toString(),priceCounted.toString(),
                    dateText.text.toString(),timeTextStart.text.toString(),endTime,"practice")
            }
        }

    }
    private fun countPrice(){
        val data2=intent.getParcelableExtra<AreaResponse.Area>("data2")
        priceCounted=(data2.dayPrice.toInt() * timeDurasi.text.toString().toInt())
        priceText.text="Rp. ${priceCounted}"
        val countHour:Int=when(hur+timeDurasi.text.toString().toInt()>24){
            true-> (hur+timeDurasi.text.toString().toInt())-24
            else-> (hur+timeDurasi.text.toString().toInt())
        }
        endTime="${countHour}:$minut:00"
        logD(endTime)
    }

    val calendar= Calendar.getInstance()

    val dateDialog= DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR,year)
        calendar.set(Calendar.MONTH,month)
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        updateDate()
    }
    private fun updateDate(){
        val myFormat = "yyyy-MM-dd" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateText.setText(sdf.format(calendar.getTime()))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}
