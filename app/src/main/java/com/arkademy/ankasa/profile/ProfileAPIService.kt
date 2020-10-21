package com.arkademy.ankasa.profile

import android.telecom.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProfileAPIService {
    @GET("customer/{id}")
    fun getProfile(@Path("id") id: String?) : retrofit2.Call<ProfileResponse>

    @Multipart
    @POST("customer")
    fun postProfile(@Part("id_user") idUser: RequestBody,
    @Part("id_routes") idRoutes: RequestBody,
    @Part("username") userName: RequestBody,
    @Part("phone") phone: RequestBody,
    @Part("address") address: RequestBody,
    @Part("post_code") postCode: RequestBody,
    @Part image: MultipartBody.Part?) : retrofit2.Call<FormProfileResponse>

    @Multipart
    @PUT("customer/{id}")
    fun updateProfile(@Path("id") id: String?,
                      @Part("id_user") idUser: RequestBody,
                      @Part("id_routes") idRoutes: RequestBody,
                      @Part("username") userName: RequestBody,
                      @Part("phone") phone: RequestBody,
                      @Part("address") address: RequestBody,
                      @Part("post_code") postCode: RequestBody,
                      @Part image: MultipartBody.Part?) : retrofit2.Call<Void>
}