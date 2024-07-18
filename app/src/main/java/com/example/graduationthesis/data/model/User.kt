package com.example.graduationthesis.data.model

import java.io.Serializable

data class User(
    val id : String = "",
    val name : String = "",
    val address : String = "",
    val email : String = "",
    val dateBirht : String = "",
    val gender : String = "",
    val numberPhone : String = "",
    val status : String = "",
    val passWord : String = ""
) : Serializable