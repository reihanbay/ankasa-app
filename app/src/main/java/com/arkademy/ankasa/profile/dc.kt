package com.arkademy.ankasa.profile


import com.google.gson.annotations.SerializedName

data class dc(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)