package com.arkademy.ankasa.utils.api.services

import com.arkademy.ankasa.utils.api.response.getFlightByIdResponse
import com.arkademy.ankasa.utils.api.response.getFlightResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface flightApiService {
    @GET("airlines/")
    suspend fun getSearchFlight(@Query("searchOrigin") origin: String, @Query("searchDestination") destination: String,
                                @Query("searchDeparture") departure: String, @Query("searchClass") flightClass: String) : getFlightResponse

    @GET("airlines/{id}")
    suspend fun getSearchFlightById(@Path("id") idFlight: Int) : getFlightByIdResponse
}