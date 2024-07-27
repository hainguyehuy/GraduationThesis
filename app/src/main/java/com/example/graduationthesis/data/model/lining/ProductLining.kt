package com.example.graduationthesis.data.model.lining

import java.io.Serializable

data class ProductLining(
    val idPD: String = "",
    val urlPD: String= "",
    val namePD: String= "",
    val pricePD: Double= 0.0,
    val titlePD : String= "",
    val colorPD : String= "",
    val sizePD : String= "",
    val numberSale : String = "",
    val priceSale : Double = 0.0
): Serializable{

}
