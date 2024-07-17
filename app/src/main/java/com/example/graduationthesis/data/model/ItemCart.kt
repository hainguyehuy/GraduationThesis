package com.example.graduationthesis.data.model

import android.widget.ImageView
import java.io.Serializable

data class ItemCart(
    val idItemCart: String = "",
    val nameItemCart : String = "",
    val colorItemCart : String = "",
    val priceItemCart : Double = 0.0,
//    val urlItemCart : String = "" ,
    val sizeItemCart : String = ""
    ): Serializable{

    }

