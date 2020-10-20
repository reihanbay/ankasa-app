package com.arkademy.ankasa.utils.api.response

import com.google.gson.annotations.SerializedName

data class postBookingResponse(val success: Boolean, val message: String, val data: DataResult) {
    data class DataResult(
        @SerializedName("id") val idBooking : Int,
        @SerializedName("id_airlines") val idAirlines: Int,
        @SerializedName("id_user") val idUser: Int,
        @SerializedName("total_price") val total: Int,
        @SerializedName("status") val status: String,
    )
}