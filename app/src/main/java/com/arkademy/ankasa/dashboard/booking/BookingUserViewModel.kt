package com.arkademy.ankasa.dashboard.booking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.BookingService
import kotlinx.coroutines.launch
import retrofit2.Response

class BookingUserViewModel : ViewModel() {

    val responseBookingUserById: MutableLiveData<Response<BookingUserByIdResponse>> =
        MutableLiveData()
    val responseBookingDetail: MutableLiveData<Response<BookingDetailResponse>> = MutableLiveData()

    fun getBookingUserById(id: Int) {
        viewModelScope.launch {
            val response =
                ApiClient.getApiClientTokenNullEx(null)?.create(BookingService::class.java)
                    ?.getUserBookingById(id)

            responseBookingUserById.value = response
        }

    }

    fun getBookingDetail(id: Int) {
        viewModelScope.launch {
            val response =
                ApiClient.getApiClientTokenNullEx(null)?.create(BookingService::class.java)?.getBookingDetail(id)
            responseBookingDetail.value = response
        }
    }
}