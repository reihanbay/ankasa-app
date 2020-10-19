package com.arkademy.ankasa.profile

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProfileAPIService {
    @GET("customer/{id}")
    fun getProfile(@Path("id") id: String?) : retrofit2.Call<ProfileResponse>

}