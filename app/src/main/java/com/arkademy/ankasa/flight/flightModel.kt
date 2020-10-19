package com.arkademy.ankasa.flight

data class flightModel(
    var from: String,
    var destination: String,
    var terminal: String,
    var timeFrom: String,
    var timeDestination: String,
    var gate: String,
    var flight: String,
    var priceChild: Int,
    var priceAdults: Int
) {
    companion object {
            val firstList = flightModel(
                "IDN",
                "JPN",
                "A",
                "12:33",
                "13:33",
                "221",
                "AirBanyu",
                10,
                30
            )
        }
}