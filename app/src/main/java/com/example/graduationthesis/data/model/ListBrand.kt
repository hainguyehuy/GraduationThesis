package com.example.graduationthesis.data.model

data class ListBrand(
    val title : String,
    val listBrand : List<Brand>,
    var isExpandable : Boolean = false
)
