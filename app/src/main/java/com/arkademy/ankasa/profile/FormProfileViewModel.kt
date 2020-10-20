package com.arkademy.ankasa.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.route.RouteApiService
import com.arkademy.ankasa.route.RouteResponse
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormProfileViewModel : ViewModel() {
    val isResponseSpinner = MutableLiveData<RouteResponse>()
    val isFormProfile = MutableLiveData<Boolean>()
    val isUpdateProfile = MutableLiveData<Boolean>()

    private lateinit var service: ProfileAPIService
    private lateinit var routeApiService: RouteApiService
    private lateinit var sharepref: PreferenceHelper



    fun setSharedPreference(sharepref: PreferenceHelper) {
        this.sharepref = sharepref
    }

    fun setProfileService(service: ProfileAPIService) {
        this.service = service
    }

    fun setRouteService(serviceRoute: RouteApiService) {
        this.routeApiService = serviceRoute
    }

    fun callPostProfile(idUser: RequestBody, id_routes: RequestBody, username: RequestBody,
    phone: RequestBody, address: RequestBody, post_code:RequestBody, img: MultipartBody.Part?){
        service?.postProfile(idUser, id_routes, username, phone, address, post_code, img).enqueue(object : Callback<FormProfileResponse>{

            override fun onFailure(call: Call<FormProfileResponse>, t: Throwable) { }

            override fun onResponse(
                call: Call<FormProfileResponse>,
                response: Response<FormProfileResponse>
            ) {
                Log.d("response", "${response.body()?.data}")
                isFormProfile.value = true
            }
        })
    }

    fun callUpdateProfile(idUser: RequestBody, id_routes: RequestBody, username: RequestBody,
                          phone: RequestBody, address: RequestBody, post_code:RequestBody, img: MultipartBody.Part?) {
        val idCustomer = sharepref.getString(Constants.PREF_CUSTOMER)
        Log.d("idcustomer", "$idCustomer")
        service?.updateProfile(idCustomer, idUser, id_routes, username, phone, address, post_code, img).enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("Error", " error : $t")
            }


            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                isUpdateProfile.value = true
            }

        })
    }

    fun initSpinnerLoc() {
        routeApiService.getRoute().enqueue(object : Callback<RouteResponse> {
            override fun onFailure(call: Call<RouteResponse>, t: Throwable) { }

            override fun onResponse(
                call: Call<RouteResponse>,
                response: Response<RouteResponse>
            ) {
                isResponseSpinner.value = response.body()
            }
        })
    }
}