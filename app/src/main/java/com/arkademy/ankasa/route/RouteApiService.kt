package com.arkademy.ankasa.route

import com.arkademy.ankasa.profile.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RouteApiService {


    @GET("route")
    fun getRoute() : retrofit2.Call<RouteResponse>


}