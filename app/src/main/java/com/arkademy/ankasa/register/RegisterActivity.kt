package com.arkademy.ankasa.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.arkademy.ankasa.FormProfileActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityRegisterBinding
import com.arkademy.ankasa.login.LoginActivity
import com.arkademy.ankasa.searchFlight.SearchFlightActivity

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.btnSignIn.setOnClickListener(this)
        binding.btnSignUp.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        binding.apply {
            when (p0) {
                btnSignIn -> {startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))}
                btnSignUp -> {startActivity(Intent(this@RegisterActivity, FormProfileActivity::class.java))}
            }
        }
    }
}