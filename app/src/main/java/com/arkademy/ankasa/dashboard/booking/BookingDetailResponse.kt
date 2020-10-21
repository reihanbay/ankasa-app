package com.arkademy.ankasa.dashboard.booking

data class BookingDetailResponse(
    val `data`: BookingDetailModel,
    val message: String,
    val success: Boolean
)