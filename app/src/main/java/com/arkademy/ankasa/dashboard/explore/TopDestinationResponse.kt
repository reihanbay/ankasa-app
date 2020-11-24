package com.arkademy.ankasa.dashboard.explore

data class TopDestinationResponse (val success: String?, val message: String?, val data: List<TopDestinationData>) {
    data class TopDestinationData (
        val city: String?,
        val country: String?,
        val id_routes: Int?,
        val image: String
    )
}