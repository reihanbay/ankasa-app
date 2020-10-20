package com.arkademy.ankasa.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse (val success: String?, val message: String, val data: DataResult? ) {
    data class DataResult(
        @SerializedName("address")
        val address: String,
        @SerializedName("city")
        val city: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("createAt")
        val createAt: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("fullname")
        val fullname: String?,
        @SerializedName("id_customer")
        val idCustomer: Int,
        @SerializedName("id_routes")
        val idRoutes: Int,
        @SerializedName("id_user")
        val idUser: Int,
        @SerializedName("image")
        val image: String,
        @SerializedName("password")
        val password: String,
        @SerializedName("phone")
        val phone: Int,
        @SerializedName("post_code")
        val postCode: Int,
        @SerializedName("updateAt")
        val updateAt: String,
        @SerializedName("user_role")
        val userRole: Int,
        @SerializedName("username")
        val username: String
    )

}