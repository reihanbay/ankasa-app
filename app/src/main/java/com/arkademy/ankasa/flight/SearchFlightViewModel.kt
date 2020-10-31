package com.arkademy.ankasa.flight

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.api.model.CitySpinnerModel
import com.arkademy.ankasa.utils.api.response.getCityResponse
import com.arkademy.ankasa.utils.api.services.CityService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SearchFlightViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
    private lateinit var service : CityService
    val spinnerLiveData = MutableLiveData<List<CitySpinnerModel>>()

    fun setSpinnerApi(service: CityService) {
        this.service = service
    }

    fun callSpinnerApi(){
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service.getAllCity()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is getCityResponse) {
                val list = response.data.map {
                    CitySpinnerModel(it.id_routes, it.city.orEmpty(), it.country.orEmpty())
                }
                spinnerLiveData.value = list
            }
        }
    }
}