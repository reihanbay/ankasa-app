package com.arkademy.ankasa.dashboard.explore

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.ExploreService
import kotlinx.coroutines.launch
import retrofit2.Response

@Suppress("CAST_NEVER_SUCCEEDS")
class ExploreViewModel: ViewModel() {

    val responseTrendingDestination: MutableLiveData<Response<TrendingResponse>> = MutableLiveData()
    val responseTopDestination: MutableLiveData<Response<TopDestinationResponse>> = MutableLiveData()

    fun getTrendingDestination() {
        viewModelScope.launch {
            val response = ApiClient.getApiClient(null)?.create(ExploreService::class.java)?.getAllTrendingDest()
            if (response!!.isSuccessful) {
                responseTrendingDestination.value = response
            } else {
                responseTrendingDestination.value = response.errorBody() as Response<TrendingResponse>
            }
        }
    }

    fun getTopDestination() {
        viewModelScope.launch {
            val response = ApiClient.getApiClient(null)?.create(ExploreService::class.java)?.getAllTopDest()
            if (response!!.isSuccessful) {
                responseTopDestination.value = response
            } else {
                responseTopDestination.value = response.errorBody() as Response<TopDestinationResponse>
            }
        }
    }
}