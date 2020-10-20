package com.arkademy.ankasa.utils.api.services

import com.arkademy.ankasa.utils.api.response.postBookingResponse
import retrofit2.http.Field
import retrofit2.http.POST

interface BookingService {
    @POST("booking/")
    suspend fun postBooking(
        @Field("id_airlines") idAirlines: Int,
        @Field("id_user") idUser: Int,
        @Field("total_price") total: Int,
        @Field("status") status: String
    ): postBookingResponse
}