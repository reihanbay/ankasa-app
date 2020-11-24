package com.arkademy.ankasa.dashboard.explore

data class TrendingResponse (val success: String, val message: String, val data: List<TrendingData>) {
    data class TrendingData(
        val city: String?,
        val country: String?,
        val id_routes: Int?,
        val image: String
    )
}