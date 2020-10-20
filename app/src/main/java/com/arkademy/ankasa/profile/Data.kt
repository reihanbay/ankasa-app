package com.arkademy.ankasa.profile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_routes")
    val idRoutes: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("post_code")
    val postCode: String,
    @SerializedName("username")
    val username: String
)