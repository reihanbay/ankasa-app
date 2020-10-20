package com.arkademy.ankasa.profile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city")
    val city: String,
    @SerializedName("fullname")
    val fullname: String,
    @SerializedName("id_customer")
    val idCustomer: Int,
    @SerializedName("image")
    val image: String
)