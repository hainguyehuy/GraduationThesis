package com.example.graduationthesis.data.model
import java.io.Serializable

data class ReceiptPD(
    val idPD: String = "",
    val urlPD: String = "",
    val namePD: String = "",
    val pricePD: Double = 0.0,
    val titlePD: String = "",
    val colorPD: String = "",
    val sizePD: String = "",
    val quantityOfGoods: Double = 0.0
) : Serializable
