package com.arkademy.ankasa.dashboard.explore

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.ExploreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

@Suppress("CAST_NEVER_SUCCEEDS")
class ExploreViewModel: ViewModel() {

    val responseTrendingDestination: MutableLiveData<Response<TrendingResponse>> = MutableLiveData()
    val responseTopDestination: MutableLiveData<Response<TopDestinationResponse>> = MutableLiveData()
    val responseLocation: MutableLiveData<Response<LocationResponse>> = MutableLiveData()

    fun getTrendingDestination() {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)?.getAllTrendingDest()}
            if (response!!.isSuccessful) {
                responseTrendingDestination.value = response
            } else {
                responseTrendingDestination.value = response.errorBody() as Response<TrendingResponse>
            }
        }
    }

    fun getTopDestination() {
        viewModelScope.launch {
            val response = ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)?.getAllTopDest()
            if (response!!.isSuccessful) {
                responseTopDestination.value = response
            } else {
                responseTopDestination.value = response.errorBody() as Response<TopDestinationResponse>
            }
        }
    }

    fun getLocation(){
        viewModelScope.launch {
            val response = ApiClient.getApiClientTokenNullEx(null)?.create(ExploreService::class.java)?.getAllLocation()
            if (response!!.isSuccessful) {
                responseLocation.value = response
            } else {
                responseLocation.value = response.errorBody() as Response<LocationResponse>
            }
        }
    }
}