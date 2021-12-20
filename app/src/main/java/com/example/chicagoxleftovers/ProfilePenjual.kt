package com.example.chicagoxleftovers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chicagoxleftovers.databinding.ActivityProfilePenjualBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_daftar_produk.*
import kotlinx.android.synthetic.main.activity_profile_penjual.*

class ProfilePenjual : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityProfilePenjualBinding

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mRating:MutableList<Rating>
    private lateinit var listAdapter: RatingAdapter

    private lateinit var mToko:MutableList<Toko>

    private var rating : Double= 0.0
    private var ratingRata : Double= 0.0
    private var userRating : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilePenjualBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ratingList.setHasFixedSize(true)
        ratingList.layoutManager = LinearLayoutManager(this@ProfilePenjual)
        mRating = ArrayList()
        listAdapter = RatingAdapter(this@ProfilePenjual, mRating)
        ratingList.adapter = listAdapter

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("rating")

        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mRating.clear()
                for (RatingSnapshot in snapshot.children){
                    if(firebaseAuth.currentUser!!.uid == RatingSnapshot.getValue(Menu::class.java)!!.id_toko) {
                        val upload = RatingSnapshot.getValue(Rating::class.java)
                        upload!!.id_Rating = RatingSnapshot.key
                        rating =  rating + upload.rating
                        userRating =+ 1.0
                        mRating.add(upload)
                    }
                }
                ratingRata = rating/userRating
                binding.tvRating.text = ratingRata.toString()

                listAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ProfilePenjual,error.message, Toast.LENGTH_SHORT).show()
            }

        })

        //init firebaseAuth

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
                            val nomorTlp = TokoSnapshot.getValue(Toko::class.java)!!.no_Tlp
                            binding.tvAlamat.text = alamat
                            binding.tvNamaToko.text = namaToko
                            binding.tvNoTlp.text = nomorTlp
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })



        }
    }

    fun fKembali(view: android.view.View) {
        startActivity(Intent(this@ProfilePenjual, BerandaPenjual::class.java))
        finish()
    }
}