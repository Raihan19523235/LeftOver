package com.example.chicagoxleftovers

import com.google.firebase.database.Exclude

class Rating (
    var id_Rating: String? = null,
    var id_Toko: String? = null,
    var id_Pesanan: String? = null,
    var id_user: String? = null,
    @get:Exclude
    @set:Exclude
    var namaPerating: String? = null,
    var rating: Float = 0F,
    var ulasan: String? = null,
)