package com.arkademy.ankasa.utils.api.response

import com.google.gson.annotations.SerializedName

class getFlightResponse(val success: Boolean, val message: String, val data: List<Flight>) {
    data class Flight(
        @SerializedName("id_airlines") val idAirlines: Int,
        @SerializedName("name_airlines") val nameFlight: String,
        @SerializedName("price_child") val child: String,
        @SerializedName("price_adult") val adult: String,
        @SerializedName("code") val codeFlight: String,
        @SerializedName("init_origin") val initOrigin: String,
        @SerializedName("init_destination") val initDestination: String,
        @SerializedName("time_from") val timeFrom: String,
        @SerializedName("time_destination") val timeDestination: String,
        @SerializedName("terminal") val terminal: String,
        @SerializedName("class_airlines") val classFlight: String,
        @SerializedName("facilities") val facilities: String,
        @SerializedName("time_departure") val departure: String,
        @SerializedName("code_route_origin") val cityOrigin: String,
        @SerializedName("code_route_destination") val cityDestination: String,
        @SerializedName("country_origin") val countryOrigin: String,
        @SerializedName("country_destination") val countryDestination: String
    )
}