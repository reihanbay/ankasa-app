package com.arkademy.ankasa.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class ProfileViewModel: ViewModel() {

    private lateinit var service: ProfileAPIService
    private lateinit var sharepref: PreferenceHelper

    val isResponseProfile = MutableLiveData<ProfileResponse>()


    fun setSharedPreference(sharepref: PreferenceHelper) {
        this.sharepref = sharepref
    }

    fun setProfileService(service: ProfileAPIService) {
        this.service = service
    }

    fun callProfileApi() {
        val idUser = sharepref.getString(Constants.KEY_ID)
        service?.getProfile(idUser).enqueue(object :retrofit2.Callback<ProfileResponse>{

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                Log.d("idcuss", "${response.body()?.data?.idCustomer}")
                val a = sharepref.putString(Constants.PREF_CUSTOMER, response.body()?.data?.idCustomer)
                Log.d("idcusssss", "$a")
                Log.d("responseget", "${response.body()}")
                isResponseProfile.value = response.body()
            }

        })
    }

}