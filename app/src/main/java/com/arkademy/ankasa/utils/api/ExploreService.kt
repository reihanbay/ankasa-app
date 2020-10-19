package com.arkademy.ankasa.utils.api

import com.arkademy.ankasa.dashboard.explore.TopDestinationResponse
import com.arkademy.ankasa.dashboard.explore.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExploreService {

    @GET("route/trending")
    suspend fun getAllTrendingDest(): Response<TrendingResponse>

    @GET("route/top10")
    suspend fun getAllTopDest(): Response<TopDestinationResponse>
}