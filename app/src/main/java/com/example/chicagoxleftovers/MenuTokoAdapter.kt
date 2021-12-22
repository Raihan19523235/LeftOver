package com.example.chicagoxleftovers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.squareup.picasso.Picasso

class MenuTokoAdapter(var mContext : Context, var rvListMenuToko : List<Menu>) : RecyclerView.Adapter<MenuTokoAdapter.MyViewHolder>() {

    private lateinit var listenerClickMenu : MenuTokoAdapter.setOnClickMenu

    fun clickButtonMenu(v : MenuTokoAdapter.setOnClickMenu){
        this.listenerClickMenu = v
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuTokoAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_produk,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = rvListMenuToko[position]

//        holder.foto_produk.loadImage(currentitem.foto_produk)
//        Glide.with(mContext)
//            .load(currentitem.nama_menu!!.toUri())
//            .into(holder.foto_produk)
        holder.nama_menu.text = currentitem.nama_menu
        holder.harga_diskon.text = currentitem.harga_diskon.toString()
        holder.deskripsi.text = currentitem.deskripsi

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, MetodePembayaran::class.java)

            intent.putExtra("EXTRA", holder.nama_menu.text)
            intent.putExtra("EXTRA_id", holder.itemId)
            intent.putExtra("EXTRA_harga",holder.harga_diskon.text)
            intent.putExtra("EXTRA_tanggal",holder.deskripsi.text)

            it.context.startActivity(intent)

        }
//        Glide.with(mContext)
//            .load(currentitem.foto_produk)


//        holder.listProduk.setOnClickListener {
//            listenerClickMenu.listProduk(rvListProduk[position])
//        }

    }

    override fun getItemCount(): Int {
        return rvListMenuToko.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //        var foto_produk: ImageView
        var nama_menu: TextView
        var harga_diskon: TextView
        var deskripsi: TextView
//        var listProduk : RecyclerView

        init {
//            foto_produk = itemView.findViewById(R.id.ivMenu)
            nama_menu  = itemView.findViewById(R.id.tvNamaMenuToko)
            harga_diskon = itemView.findViewById(R.id.tvDeskripsiMenuToko)
            deskripsi = itemView.findViewById(R.id.tvTanggal)
//            listProduk = itemView.findViewById(R.id.rvListProduk)
        }

    }
    interface setOnClickMenu{
        fun listProduk(v : Menu)
    }
}