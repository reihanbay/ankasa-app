package com.arkademy.ankasa.utils.api.response

import com.google.gson.annotations.SerializedName

data class getCityResponse(val success: Boolean, val message: String, val data: List<City>)  {
    data class City(
        @SerializedName("id_routes") val id_routes: Int,
        @SerializedName("city") val city: String,
        @SerializedName("country") val country: String,
        @SerializedName("images") val image: String
    )
}