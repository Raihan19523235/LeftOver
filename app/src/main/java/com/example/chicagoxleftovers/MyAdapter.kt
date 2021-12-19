package com.example.chicagoxleftovers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//class MyAdapter(private val menuList : ArrayList<Menu>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
class MyAdapter( var mContext : Context, var menuList : List<Menu>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.menu_item,
        parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = menuList[position]

        holder.foto_produk.loadImage(currentitem.foto_produk)
        holder.nama_menu.text = currentitem.nama_menu
        holder.harga_asli.text = currentitem.harga_asli.toString()
        holder.tanggal_produksi.text = currentitem.tanggal_produksi


//28.39
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var foto_produk = itemView.findViewById<ImageView>(R.id.ivMenu)
        var nama_menu  = itemView.findViewById<TextView>(R.id.tvNamaMenu)
        var harga_asli = itemView.findViewById<TextView>(R.id.tvHargaAsli)
        var tanggal_produksi = itemView.findViewById<TextView>(R.id.tvTanggal)
        var editData = itemView.findViewById<Button>(R.id.btnEdit)

    }
}