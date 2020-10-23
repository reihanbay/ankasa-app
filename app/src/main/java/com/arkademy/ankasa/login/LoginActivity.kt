package com.arkademy.ankasa.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.MainActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityLoginBinding
import com.arkademy.ankasa.forgot.ForgotPassActivity
import com.arkademy.ankasa.profile.FormProfileActivity
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper

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
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.setSharedPreference(sharepref)

        if (service != null) {
            viewModel.setLoginService(service)
        }

        binding.btnLogin.setOnClickListener {
            viewModel.callAuthApi(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        subscribeLiveData()
    }

    private fun moveIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun moveReset() {
        startActivity(Intent(this, ForgotPassActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        if (sharepref.getBoolean(Constants.PREF_IS_LOGIN)!!) {
            moveIntent()
            finish()
        }
    }

    private fun subscribeLiveData() {
        viewModel.isLoginLiveData.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Login Succcess", Toast.LENGTH_SHORT).show()
                moveIntent()
                finish()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isResponse.observe(this, Observer {
            if (it) {

            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isRegister.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, FormProfileActivity::class.java))
            } else {
                moveIntent()
            }

        })
        viewModel.isToast.observe(this, Observer {
            if (it) {

            } else {
                Toast.makeText(this, "Fill The Blank!", Toast.LENGTH_SHORT).show()
            }
        })

    }

}