package com.arkademy.ankasa.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.MainActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityLoginBinding
import com.arkademy.ankasa.forgot.ForgotPassActivity
import com.arkademy.ankasa.profile.FormProfileActivity
import com.arkademy.ankasa.profile.ProfileAPIService
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharepref: PreferenceHelper
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.tvReset.setOnClickListener {
            moveReset()
        }

        sharepref = PreferenceHelper(this)
        val service = ApiClient.getApiClientToken(this)?.create(AuthApiService::class.java)
        val serviceProfile = ApiClient.getApiClientToken(this)?.create(ProfileAPIService::class.java)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        if (service != null) {
            viewModel.setLoginService(service)
        }
        if (serviceProfile != null) {
            viewModel.setCheckProfileService(serviceProfile)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.callAuthApi(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        subscribeLiveData()
    }

    private fun moveIntent(code: String) {
        if (code == "form") {
            startActivity(Intent(this, FormProfileActivity::class.java).putExtra("form", true))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun moveReset() {
        startActivity(Intent(this, ForgotPassActivity::class.java))
        finish()
    }


    private fun subscribeLiveData() {
        viewModel.isLoginLiveData.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Login Succcess", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isToast.observe(this, Observer {
            if (it) {

            } else {
                Toast.makeText(this, "Fill The Field!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.saveLiveData.observe(this, Observer {
            sharepref.putString(Constants.KEY_TOKEN, it.data?.token)
            sharepref.putBoolean(Constants.PREF_IS_LOGIN, true)
            sharepref.putString(Constants.KEY_ID, it.data?.idUser)
            sharepref.putString(Constants.PREF_USERNAME, it.data?.fullname)
            viewModel.checkAuthApi(it.data?.idUser)
        })

        viewModel.intentLiveData.observe(this, Observer {
            if (it) {
                moveIntent("form")
                finish()
            } else {
                moveIntent("main")
                finish()
            }
        })

    }

}