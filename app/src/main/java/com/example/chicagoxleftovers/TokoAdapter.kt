package com.example.chicagoxleftovers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TokoAdapter (var mContext : Context, var rvListToko : List<Toko>) : RecyclerView.Adapter<TokoAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_store,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TokoAdapter.MyViewHolder, position: Int) {
        val currentitem = rvListToko[position]

//        holder.foto_produk.loadImage(currentitem.foto_produk)
        holder.nama_toko.text = currentitem.nama_toko
        holder.alamat_toko.text = currentitem.alamat_toko.toString()
//        holder.tanggal_produksi.text = currentitem.tanggal_produksi
    }

    override fun getItemCount(): Int {
        return rvListToko.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        //        var foto_toko: ImageView
        var nama_toko: TextView
        var alamat_toko: TextView
//        var tanggal_produksi: TextView

        init {
//            foto_produk = itemView.findViewById(R.id.ivMenu)
            nama_toko  = itemView.findViewById(R.id.tvShopName)
            alamat_toko = itemView.findViewById(R.id.tvShopAddress)
//            tanggal_produksi = itemView.findViewById(R.id.tvTanggal)

        }

    }
}
