package com.arkademy.ankasa.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.profile.ProfileAPIService
import com.arkademy.ankasa.profile.ProfileResponse
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel : ViewModel(), CoroutineScope {
    val isLoginLiveData = MutableLiveData<Boolean>()
    val isResponse = MutableLiveData<Boolean>()
    val isToast = MutableLiveData<Boolean>()
    val saveLiveData = MutableLiveData<LoginResponse>()
    val intentLiveData = MutableLiveData<Boolean>()

    private lateinit var service: AuthApiService
    private lateinit var serviceProfile: ProfileAPIService

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setLoginService(service: AuthApiService) {
        this.service = service
    }

    fun setCheckProfileService(service: ProfileAPIService) {
        this.serviceProfile = service
    }

    fun checkAuthApi(id: String?) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    serviceProfile.checkProfile(id)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is ProfileResponse) {
                if (response.data == null) {
                    intentLiveData.value = true
                } else {
                    intentLiveData.value = false
                }
            }
        }
    }

    fun callAuthApi(email: String, password: String) {
        launch {
            val response = withContext(Dispatchers.IO) {
                try {
                    service?.loginRequest(
                        email, password
                    )
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            if (response is LoginResponse) {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    isToast.value = true
                    if (response.data?.userRole == 1) {
                        saveLiveData.value = response
                        isLoginLiveData.value = true
                    } else {
                        isLoginLiveData.value = false
                    }
                } else {
                    isToast.value = false
                }
            } else {
                isLoginLiveData.value = false
            }
        }
    }


}