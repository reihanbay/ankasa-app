package com.arkademy.ankasa.flight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.api.response.PostBookingResponse
import com.arkademy.ankasa.utils.api.response.getFlightByIdResponse
import com.arkademy.ankasa.utils.api.services.BookingService
import com.arkademy.ankasa.utils.api.services.flightApiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class FlightDetailViewModel : ViewModel(), CoroutineScope {

    private lateinit var serviceGet: flightApiService
    private lateinit var servicePost: BookingService
    val detailLiveData = MutableLiveData<getFlightByIdResponse>()
    val isBookedLiveData = MutableLiveData<Boolean>()

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setGetService(service: flightApiService) {
        this.serviceGet = service
    }

    fun setPostService(service: BookingService) {
        this.servicePost = service
    }

    fun callGetApi(id: Int) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    serviceGet.getSearchFlightById(id)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is getFlightByIdResponse) {
                detailLiveData.value = response
            }
        }
    }

    fun callPostApi(idAirlines: Int, idUser: Int, total: Int, status: String) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                        servicePost.postBooking(idAirlines, idUser, total, status)
                } catch (e: Throwable){
                    e.printStackTrace()
                }
            }
            isBookedLiveData.value = response is PostBookingResponse
        }
    }
}