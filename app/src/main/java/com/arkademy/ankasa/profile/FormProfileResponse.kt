package com.arkademy.ankasa.profile


import com.google.gson.annotations.SerializedName

data class FormProfileResponse(val success: String?, val message: String, val data: DataResult?) {

    data class DataResult (
        @SerializedName("address")
        val address: String,
        @SerializedName("id")
        val id: String,
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
}