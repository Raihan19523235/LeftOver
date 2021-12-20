package com.example.chicagoxleftovers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chicagoxleftovers.databinding.ActivityBerandaPembeliBinding
import com.example.chicagoxleftovers.databinding.ActivityBerandaPenjualBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_beranda_pembeli.*
import kotlinx.android.synthetic.main.activity_daftar_produk.*

class BerandaPembeli : AppCompatActivity() {

    //viewBinding
    private lateinit var binding: ActivityBerandaPembeliBinding

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private var mImageUri: Uri? = null
    private var mStorage: FirebaseStorage? = null

    private var mDatabaseRefToko: DatabaseReference? = null
    private var mDBListenerToko: ValueEventListener? = null
    private var mImageUriToko: Uri? = null
    private var mStorageToko: FirebaseStorage? = null

    private lateinit var mMenu:MutableList<Menu>
    private lateinit var listAdapter: MenuAdapter

    private lateinit var mToko:MutableList<Toko>
    private lateinit var listAdapterToko: TokoAdapter

    private lateinit var mUser:MutableList<User>

    private lateinit var recyclerViewHorizontal: RecyclerView
    private lateinit var recyclerViewVertical: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaPembeliBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        FirebaseDatabase.getInstance().getReference("user")

        recyclerViewHorizontal = findViewById(R.id.rvListProduk)
        recyclerViewHorizontal.layoutManager = LinearLayoutManager(this@BerandaPembeli,
            LinearLayoutManager.HORIZONTAL, false)

        recyclerViewVertical = findViewById(R.id.rvListToko)
        recyclerViewVertical.layoutManager = LinearLayoutManager(this@BerandaPembeli,
            LinearLayoutManager.VERTICAL, false)

//        rvListToko.setHasFixedSize(true)
//        rvListToko.layoutManager = LinearLayoutManager(this@BerandaPembeli)

        mMenu = ArrayList()
        listAdapter = MenuAdapter(this@BerandaPembeli, mMenu)
        rvListProduk.adapter = listAdapter

        mToko = ArrayList()
        listAdapterToko = TokoAdapter(this@BerandaPembeli, mToko)
        rvListToko.adapter = listAdapterToko

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("menu")

        mStorageToko = FirebaseStorage.getInstance()
        mDatabaseRefToko = FirebaseDatabase.getInstance().getReference("toko")

        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mMenu.clear()
                for (MenuSnapshot in snapshot.children){
                    val upload = MenuSnapshot.getValue(Menu::class.java)
                    upload!!.id_menu = MenuSnapshot.key
                    mMenu.add(upload)
                }
                listAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BerandaPembeli,error.message, Toast.LENGTH_SHORT).show()
            }

        })

        mDBListenerToko = mDatabaseRefToko!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mToko.clear()
                for (TokoSnapshot in snapshot.children){
                    val upload1 = TokoSnapshot.getValue(Toko::class.java)
                    upload1!!.id_user = TokoSnapshot.key
                    mToko.add(upload1)
                }
                listAdapterToko.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@BerandaPembeli,error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser


        if(firebaseUser == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else{

            mUser = ArrayList()
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("user")

            mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mUser.clear()
                    for (UserSnapshot in snapshot.children){
                        if(firebaseAuth.currentUser!!.uid == UserSnapshot.getValue(User::class.java)!!.id_user){
                            val alamat = UserSnapshot.getValue(User::class.java)!!.alamat_pembeli
                            binding.tvLokasi.text = alamat
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

        }
    }

    fun fKeJelajah(view: android.view.View) {
        startActivity(Intent(this, Jelajah::class.java))
        finish()
    }
}