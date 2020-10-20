package com.arkademy.ankasa.utils.api.model

data class getFlightModel(
    val idAirlines: Int,
    val nameFlight: String,
    val child: String,
    val adult: String,
    val codeFlight: String,
    val initOrigin: String,
    val initDestination: String,
    val timeFrom: String,
    val timeDestination: String,
    val terminal: String,
    val classFlight: String,
    val facilities: String,
    val departure: String,
    val cityOrigin: String,
    val cityDestination: String,
    val countryOrigin: String,
    val countryDestination: String
)