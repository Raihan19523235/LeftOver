package com.example.chicagoxleftovers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PemesanAdapter (var mContext : Context, var rvListPemesan : List<Pemesan>) : RecyclerView.Adapter<PemesanAdapter.MyViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemesanAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pesanan_item,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PemesanAdapter.MyViewHolder, position: Int) {
        val currentitem = rvListPemesan[position]

        holder.nama_toko.text = currentitem.namaMak
        holder.alamat_toko.text = currentitem.tanggalProd
    }

    override fun getItemCount(): Int {
        return rvListPemesan.size
    }


    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var nama_toko: TextView
        var alamat_toko: TextView

        init {
            nama_toko  = itemView.findViewById(R.id.tvNamaPemesan)
            alamat_toko = itemView.findViewById(R.id.tvAlamatPemesan)

        }

    }

}
