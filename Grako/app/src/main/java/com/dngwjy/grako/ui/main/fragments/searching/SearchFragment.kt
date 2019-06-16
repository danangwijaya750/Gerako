package com.dngwjy.grako.ui.main.fragments.searching

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.dngwjy.grako.R
import com.dngwjy.grako.ui.search.SearchResultActivity
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.search_fragment.*
import java.util.*
import java.text.SimpleDateFormat
import android.widget.TimePicker
import android.app.TimePickerDialog





/**
 * Created by wijaya on 15/06/19
 */
class SearchFragment: Fragment() {
    companion object{
        var type:String=""
        var sport:String=""
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_fragment,container,false)
    }
    val calendar=Calendar.getInstance()

    val dateDialog=DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateText.setOnClickListener {
            DatePickerDialog(this.context!!,dateDialog,calendar.get(Calendar.YEAR)
            ,calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
        timeText.setOnClickListener {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
            val minute = mcurrentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(this.context,
                TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                    timeText.setText(
                        "$selectedHour:$selectedMinute:00"
                    )
                }, hour, minute, true
            )//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        }


        buttonCari.setOnClickListener {
            when(type){
                "latihan"->{
                    val intent= Intent(this.context,SearchResultActivity::class.java)
                    intent.putExtra("sport", sport)
                    intent.putExtra("loc",spinnerLoc.selectedItemPosition.toString())
                    intent.putExtra("time",timeText.text.toString())
                    intent.putExtra("date",timeText.text.toString())
                    startActivity(intent)
                }
                "tanding"->{

                }
                "turnamen"->{

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}