package com.dngwjy.grako.ui.booking_detail

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import com.bumptech.glide.Glide
import com.dngwjy.grako.R
import com.dngwjy.grako.data.model.BookingResponse
import com.dngwjy.grako.utils.logD
import com.dngwjy.grako.utils.toastCnt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_booking_detail.*
import java.io.File
import java.net.URI

class BookingDetail : AppCompatActivity(),BookingDetailView {
    override fun isLoading(state: Boolean) {
        toastCnt("Upload Konfirmasi Pembayaran")
    }

    override fun showResult(state: Boolean) {
        if(state){
            toastCnt("Upload Konfirmasi Berhasil")
            this.finish()
        }else{
            toastCnt("Upload Konfirmasi Gagal coba beberapa saat lagi")
        }
    }

    lateinit var data:BookingResponse.Booking
    lateinit var presenter: BookingDetailPresenter
    lateinit var FileUri: Uri
    private var isImageSelected=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_detail)
        supportActionBar?.hide()
        val fAuth=FirebaseAuth.getInstance()
        val fStore=FirebaseStorage.getInstance().reference
        presenter=BookingDetailPresenter(this,fAuth,fStore)
        data=intent.getParcelableExtra("data")
        setData()

        buttonUploadFoto.setOnClickListener {
            if(data.status=="verified"){
                toastCnt("Status Booking Sudah diverifikasi")
            }else{
                val intent= Intent().setType("image/*")
                intent.action=Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent,"Select Image"),7)
            }
        }
        buttonCofrim.setOnClickListener {
            if(isImageSelected){
                presenter.uploadPayment(data.id.toString(),FileUri,fileExtension(FileUri))
            }else{
                toastCnt("Silahkan Pilih foto Bukti pembayaran")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==7&&resultCode== Activity.RESULT_OK&&data!=null&&data.data!=null){
            FileUri= data.data
            try{
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver,FileUri)
                imagePlace.setImageBitmap(bitmap)
                logD("image file $FileUri")
                isImageSelected=true
                logD("$isImageSelected")
            }catch (ex:Exception){
                Log.d("exection",ex.localizedMessage)
            }
        }else{
            logD("kosong")
        }
    }


    fun fileExtension(fileUri: Uri): String {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(fileUri))
    }

    private fun setData(){
        priceBook.text="Rp. ${data.price}"
        tanggalBook.text="Tanggal : ${data.date}"
        timeBook.text="Jam : ${data.startTime} - ${data.endTime}"
        if(data.photo!=null){
            Glide.with(this).load(data.photo).into(imagePlace)
            isImageSelected=true
        }
        statusBook.text="Status : ${
        when(data.status){
            null->"Menunggu Pembayaran"
            "payment"->"Menunggu Verifikasi"
            "verified"->"Menunggu Pelunasan"
            "finished"->"Selesai"
            else->""
        }
        }"
    }
}
