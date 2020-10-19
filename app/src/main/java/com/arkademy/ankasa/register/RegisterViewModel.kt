package com.arkademy.ankasa.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RegisterViewModel: ViewModel(), CoroutineScope {

    val isRegisterLiveData = MutableLiveData<Boolean>()

    private lateinit var service: AuthApiService
    private lateinit var sharepref: PreferenceHelper

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSharedPreference(sharepref: PreferenceHelper) {
        this.sharepref = sharepref
    }

    fun setRegisterService(service: AuthApiService) {
        this.service = service
    }

    fun callApiRegister(fullname: String, email: String, password: String) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service.registerRequest(fullname, email, password)
                } catch (e: Throwable) {
                    e.printStackTrace()

                    withContext(Dispatchers.Main) {
                        isRegisterLiveData.value = false
                    }
                }
            }
            Log.d("response", "$response")
            if (response is RegisterResponse) {
                sharepref.putBoolean(Constants.PREF_REGISTER, true)
                isRegisterLiveData.value = response.message == "Success Register Account!"
            }
        }
    }


}