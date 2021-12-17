package com.example.chicagoxleftovers

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chicagoxleftovers.databinding.ActivityDaftarProdukBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import kotlinx.android.synthetic.main.activity_daftar_produk.*

class DaftarProduk : AppCompatActivity() {

    private var mImageUri: Uri? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private var mStorage: FirebaseStorage? = null
    private lateinit var mMenu:MutableList<Menu>
    private lateinit var listAdapter: MyAdapter

    private val PICK_IMAGE_REQUEST = 100

    //viewBinding
    private lateinit var binding: ActivityDaftarProdukBinding

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth


    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuArrayList: ArrayList<Menu>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()



//        menuRecyclerView = findViewById(R.id.menuList)
//        menuRecyclerView.layoutManager = LinearLayoutManager(this)
//        menuRecyclerView.setHasFixedSize(true)

        menuList.setHasFixedSize(true)
        menuList.layoutManager = LinearLayoutManager(this@DaftarProduk)
        mMenu = ArrayList()
        listAdapter = MyAdapter(this@DaftarProduk, mMenu)
        menuList.adapter = listAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("menu")

        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mMenu.clear()
                for (MenuSnapshot in snapshot.children){
                    if(firebaseAuth.currentUser!!.uid == MenuSnapshot.getValue(Menu::class.java)!!.id_toko){
                        val upload = MenuSnapshot.getValue(Menu::class.java)
                        upload!!.id_menu = MenuSnapshot.key
                        mMenu.add(upload)
                    }
                }
                listAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DaftarProduk,error.message, Toast.LENGTH_SHORT).show()
            }

        })

        menuArrayList = arrayListOf<Menu>()
//        getMenuData()




    }

    fun fKembali(view: android.view.View) {
        startActivity(Intent(this@DaftarProduk, BerandaPenjual::class.java))
        finish()
    }

    fun fKeTambahMenu(view : View){
        startActivity(Intent(this, TambahMenu::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
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

            //set to textvie
//            binding.tv.text = email


        }
    }
}