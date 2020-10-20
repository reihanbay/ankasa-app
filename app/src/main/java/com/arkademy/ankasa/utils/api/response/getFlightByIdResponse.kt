package com.arkademy.ankasa.utils.api.response

import com.google.gson.annotations.SerializedName

data class getFlightByIdResponse(val success: Boolean, val message: String, val data: Flight) {
    data class Flight(
        @SerializedName("id_airlines") val idAirlines: Int,
        @SerializedName("name_airlines") val nameFlight: String,
        @SerializedName("price_child") val child: Int,
        @SerializedName("price_adult") val adult: Int,
        @SerializedName("code") val codeFlight: String,
        @SerializedName("id_route_origin") val idRouteOrigin: String,
        @SerializedName("id_route_destination") val idRouteDestination: String,
        @SerializedName("init_origin") val initOrigin: String,
        @SerializedName("init_destination") val initDestination: String,
        @SerializedName("time_from") val timeFrom: String,
        @SerializedName("time_destination") val timeDestination: String,
        @SerializedName("terminal") val terminal: String,
        @SerializedName("class_airlines") val classFlight: String,
        @SerializedName("facilities") val facilities: String,
        @SerializedName("time_departure") val departure: String,
        @SerializedName("image") val image: String,
        @SerializedName("createAt") val created: String,
        @SerializedName("updateAt") val updated: String,
    )
}