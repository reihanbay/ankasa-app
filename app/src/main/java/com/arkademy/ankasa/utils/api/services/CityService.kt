package com.arkademy.ankasa.utils.api.services

import com.arkademy.ankasa.utils.api.response.getCityResponse
import retrofit2.http.GET

interface CityService {
    @GET("route/")
    suspend fun getAllCity() : getCityResponse
}