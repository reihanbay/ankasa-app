package com.arkademy.ankasa.dashboard.explore

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.ExploreService
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.lang.NullPointerException


class ExploreViewModel : ViewModel(){

    private lateinit var service: ExploreService

    val responseTrendingDestination: MutableLiveData<Response<TrendingResponse>> = MutableLiveData()
    val responseTopDestination: MutableLiveData<Response<TopDestinationResponse>> =
        MutableLiveData()
    val responseLocation: MutableLiveData<Response<LocationResponse>> = MutableLiveData()

    fun setExploreService(service: ExploreService) {
        this.service = service
    }


    fun getTrendingDestination() {
        viewModelScope.launch {
            val response = service.getAllTrendingDest()
            responseTrendingDestination.value = response
        }
    }

//    fun getTrendingDestination() {
//        viewModelScope.launch {
//            try {
//                val response = ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)?.getAllTrendingDest()
//                responseTrendingDestination.value = response
//            } catch (e: NullPointerException) {
//                Log.d("null", "null")
//            }
//        }
//    }
//
    fun getTopDestination() {
        viewModelScope.launch {
            try {
                val response =
                    ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)?.getAllTopDest()
                responseTopDestination.value = response
            } catch (e: NullPointerException) {
                Log.d("null", "null")
            }
        }
    }
//
    fun getLocation() {
        viewModelScope.launch {
            try {
                val response =
                    ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)
                        ?.getAllLocation()
                responseLocation.value = response
            } catch (e: NullPointerException) {
                Log.d("Null", "null")
            }

        }
    }
}
