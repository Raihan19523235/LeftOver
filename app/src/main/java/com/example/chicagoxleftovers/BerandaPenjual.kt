package com.example.chicagoxleftovers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.chicagoxleftovers.databinding.ActivityBerandaPenjualBinding
import com.example.chicagoxleftovers.databinding.ActivityLoginPenjualBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.lang.StringBuilder

class BerandaPenjual : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityBerandaPenjualBinding

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    private lateinit var mToko:MutableList<Toko>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaPenjualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        FirebaseDatabase.getInstance().getReference("menu")

        //get data
        var getdata = object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var sb = StringBuilder()
                for(i in p0.children){
//                    if()
                }
            }
        }
    }



    fun fKeTambahMenu(view : View){
        startActivity(Intent(this, TambahMenu::class.java))
        finish()
    }

    fun fKeDaftarProduk(view : View){
        startActivity(Intent(this, DaftarProduk::class.java))
        finish()
    }

    fun fLogout(view : View){
        firebaseAuth.signOut()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser


        if(firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{

            //login get and dhow user info
            val email = firebaseUser.email

            mToko = ArrayList()
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("toko")

            mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mToko.clear()
                    for (TokoSnapshot in snapshot.children){
                        if(firebaseAuth.currentUser!!.uid == TokoSnapshot.getValue(Toko::class.java)!!.id_user){
                            val alamat = TokoSnapshot.getValue(Toko::class.java)!!.alamat_toko
                            val namaToko = TokoSnapshot.getValue(Toko::class.java)!!.nama_toko
                            binding.tvAlamat.text = alamat
                            binding.tvNamaToko.text = namaToko
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

//            val Alamat = FirebaseDatabase.getInstance().getReference("Toko")

            //set to textvie
            binding.tvAlamat.text = email

        }
    }
}