package com.example.chicagoxleftovers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(var mContext : Context, var rvListProduk : List<Menu>) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_produk,
            parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = rvListProduk[position]

//        holder.foto_produk.loadImage(currentitem.foto_produk)
        holder.nama_menu.text = currentitem.nama_menu
        holder.harga_diskon.text = currentitem.harga_diskon.toString()
        holder.tanggal_produksi.text = currentitem.tanggal_produksi


    }

    override fun getItemCount(): Int {
        return rvListProduk.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        var foto_produk: ImageView
        var nama_menu: TextView
        var harga_diskon: TextView
        var tanggal_produksi: TextView

        init {
//            foto_produk = itemView.findViewById(R.id.ivMenu)
            nama_menu  = itemView.findViewById(R.id.tvNamaProduk)
            harga_diskon = itemView.findViewById(R.id.tvHargaAsli)
            tanggal_produksi = itemView.findViewById(R.id.tvTanggal)

        }

    }
}