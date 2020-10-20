package com.arkademy.ankasa.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse (val success: String?, val message: String, val data: DataResult? ) {
    data class DataResult(
        @SerializedName("city")
        val city: String,
        @SerializedName("fullname")
        val fullname: String,
        @SerializedName("id_customer")
        val idCustomer: String,
        @SerializedName("image")
        val image: String
    )

}