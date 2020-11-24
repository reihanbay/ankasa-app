package com.arkademy.ankasa.login

import com.google.gson.annotations.SerializedName

class LoginResponse (val success: String?, val message: String, val data: DataResult?) {

    data class DataResult(
        @SerializedName("email")
        val email: String,
        @SerializedName("fullname")
        val fullname: String,
        @SerializedName("id_user")
        val idUser: String,
        @SerializedName("token")
        val token: String,
        @SerializedName("user_role")
        val userRole: Int
    )

}