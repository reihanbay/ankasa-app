package com.arkademy.ankasa.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.profile.FormProfileActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityRegisterBinding
import com.arkademy.ankasa.login.LoginActivity
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.AuthApiService
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sharePreferencesHelper: PreferenceHelper
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        sharePreferencesHelper = PreferenceHelper(this)
        val service = ApiClient.getApiClientToken(this)?.create(AuthApiService::class.java)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        viewModel.setSharedPreference(sharePreferencesHelper)

        if (service != null) {
            viewModel.setRegisterService(service)
        }

        binding.btnSignIn.setOnClickListener { startActivity(Intent(this@RegisterActivity, LoginActivity::class.java)) }
        binding.btnSignUp.setOnClickListener{
            viewModel.callApiRegister(
                binding.etFullname.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
        subscribeLiveData()
    }


    private fun subscribeLiveData() {
        viewModel.isRegisterLiveData.observe(this, Observer {
            Log.d("register", "$it")
            if (it) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.isToast.observe(this, Observer {
            if (it){

            }
            else {
                Toast.makeText(this, "Fill The Blank", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View?) {
        binding.apply {
            when (v) {
                btnSignIn -> {startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))}
            }
        }
    }
}