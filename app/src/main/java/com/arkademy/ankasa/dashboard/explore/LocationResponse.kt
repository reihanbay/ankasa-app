package com.arkademy.ankasa.dashboard.explore

data class LocationResponse(
    val `data`: List<LocationModel>,
    val message: String,
    val success: Boolean
)