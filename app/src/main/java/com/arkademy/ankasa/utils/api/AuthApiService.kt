package com.arkademy.ankasa.utils.api

import com.arkademy.ankasa.login.LoginResponse
import com.arkademy.ankasa.register.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {
    @FormUrlEncoded
    @POST("users/login")
    suspend fun loginRequest(@Field("email") email: String?,
                             @Field("password") password: String?) : LoginResponse

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerRequest(@Field("fullname") fullname: String?,
                                @Field("email") email: String?,
                                @Field("password") password: String?) : RegisterResponse
}