package com.arkademy.ankasa.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModel: ViewModel(), CoroutineScope {
    val isLoginLiveData = MutableLiveData<Boolean>()
    val isRegister = MutableLiveData<Boolean>()

    private lateinit var service: AuthApiService
    private lateinit var sharepref: PreferenceHelper

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main

    fun setSharedPreference(sharepref: PreferenceHelper) {
        this.sharepref = sharepref
    }

    fun setLoginService(service: AuthApiService) {
        this.service = service
    }

    fun callAuthApi(email: String, password: String) {
//        Log.d("email", "$email")
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
                Log.d("response", "${response.data}")
                if (response.data?.userRole == 1) {
                    sharepref.putString(Constants.KEY_TOKEN, response.data.token)
                    sharepref.putBoolean(Constants.PREF_IS_LOGIN, true)
                    sharepref.putString(Constants.KEY_ID, response.data.idUser)
                    sharepref.putString(Constants.PREF_USERNAME, response.data.fullname)
                    val reg = sharepref.getBoolean(Constants.PREF_REGISTER)
                    isLoginLiveData.value = true
                    if (reg != null) {
                        isRegister.value = reg == true
                    }
                }
                else {
                    isLoginLiveData.value = false
                }
            }
        }
    }



}