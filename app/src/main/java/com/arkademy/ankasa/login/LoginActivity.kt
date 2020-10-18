package com.arkademy.ankasa.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.arkademy.ankasa.MainActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.booking.DetailBookingActivity
import com.arkademy.ankasa.databinding.ActivityLoginBinding
import com.arkademy.ankasa.forgot.ForgotPassActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.tvReset.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            binding.tvReset -> {
                val intent = Intent(this, ForgotPassActivity::class.java)
                startActivity(intent)
            }
            binding.btnLogin -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}