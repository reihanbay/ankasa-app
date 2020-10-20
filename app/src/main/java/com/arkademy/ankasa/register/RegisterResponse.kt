package com.arkademy.ankasa.register

import com.google.gson.annotations.SerializedName

data class RegisterResponse (val success: String?, val message: String?, val data: DataResult?) {

    data class DataResult(

        @SerializedName("id") val id:String?,
        @SerializedName("fullname") val username:String?,
        @SerializedName("email") val email:String?,
        @SerializedName("user_role") val user_role:String?,
        @SerializedName("updateAt") val updateAt:String?,
        @SerializedName("createAt") val createAt:String?

    )


}