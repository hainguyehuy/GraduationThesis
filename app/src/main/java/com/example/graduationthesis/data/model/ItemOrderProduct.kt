package com.example.graduationthesis.data.model

import java.io.Serializable

data class ItemOrderProduct(
    val date : String = "",
    val priceOderPD : Double =0.0,
    val img : String ="",
    val nameOderPD : String =""
) : Serializable
