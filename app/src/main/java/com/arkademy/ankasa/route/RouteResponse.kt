package com.arkademy.ankasa.route

import com.google.gson.annotations.SerializedName

class RouteResponse (val message: String, val success: Boolean, val data: List<DataResult>? ) {

    class DataResult (
        @SerializedName("city")
        val city: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("id_routes")
        val idRoutes: String,
        @SerializedName("image")
        val image: String
    )

}