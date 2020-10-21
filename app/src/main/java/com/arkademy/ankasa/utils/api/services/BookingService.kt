package com.arkademy.ankasa.utils.api.services


import com.arkademy.ankasa.utils.api.response.PostBookingResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import com.arkademy.ankasa.dashboard.booking.BookingDetailResponse
import com.arkademy.ankasa.dashboard.booking.BookingUserByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BookingService {
    @FormUrlEncoded
    @POST("booking/")
    suspend fun postBooking(
        @Field("id_airlines") idAirlines: Int,
        @Field("id_user") idUser: Int,
        @Field("total_price") total: Int,
        @Field("status") status: String
    ): PostBookingResponse

    @GET("booking/users/{id}")
    suspend fun getUserBookingById(
        @Path("id") id: Int
    ): Response<BookingUserByIdResponse>

    @GET("booking/{id}")
    suspend fun getBookingDetail(
        @Path("id") id: Int
    ): Response<BookingDetailResponse>
}