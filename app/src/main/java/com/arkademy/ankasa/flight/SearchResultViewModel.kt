package com.arkademy.ankasa.flight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.api.model.getFlightModel
import com.arkademy.ankasa.utils.api.response.getFlightResponse
import com.arkademy.ankasa.utils.api.services.flightApiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchResultViewModel : ViewModel(), CoroutineScope {
    private lateinit var service: flightApiService
    val flightLiveData = MutableLiveData<List<getFlightModel>>()
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setFlightService(service: flightApiService) {
        this.service = service
    }

    fun callFlightService(
        origin: String,
        destination: String,
        departure: String,
        flightClass: String
    ) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getSearchFlight(origin, destination, departure, flightClass)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is getFlightResponse) {
                var list = response.data.map {
                    getFlightModel(
                        it.idAirlines,
                        it.nameFlight,
                        it.child,
                        it.adult,
                        it.codeFlight,
                        it.initOrigin,
                        it.initDestination,
                        it.timeFrom,
                        it.timeDestination,
                        it.terminal,
                        it.classFlight,
                        it.facilities,
                        it.departure,
                        it.cityOrigin,
                        it.cityDestination,
                        it.countryOrigin,
                        it.countryDestination
                    )
                }
                flightLiveData.value = list

            }
        }
    }
}