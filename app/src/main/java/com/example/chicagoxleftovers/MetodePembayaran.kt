package com.example.chicagoxleftovers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MetodePembayaran : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metode_pembayaran)
    }

    fun fKeNotifBerhasil(view : View){
        val intKeNotif= Intent(this, NotifBerhasil::class.java)
        startActivity(intKeNotif)
    }
}