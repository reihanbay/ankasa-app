package com.arkademy.ankasa.utils.api.model

data class getFlightByIdModel(
    val idAirlines: Int,
    val nameFlight: String,
    val child: String,
    val adult: String,
    val codeFlight: String,
    val idRouteOrigin: String,
    val idRouteDestination: String,
    val initOrigin: String,
    val initDestination: String,
    val timeFrom: String,
    val timeDestination: String,
    val terminal: String,
    val classFlight: String,
    val facilities: String,
    val departure: String,
    val image: String,
    val createAt: String,
    val updateAt: String,
)