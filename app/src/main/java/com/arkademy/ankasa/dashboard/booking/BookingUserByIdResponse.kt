package com.arkademy.ankasa.dashboard.booking

data class BookingUserByIdResponse(
    val `data`: List<BookingUserData>,
    val message: String,
    val success: Boolean
) {
    data class BookingUserData (
        val code: String,
        val init_destination: String,
        val init_origin: String,
        val name_airlines: String,
        val status: String,
        val time_departure: String,
        val time_from: String,
        val total_price: String
    )
}