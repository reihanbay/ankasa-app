package com.arkademy.ankasa.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.arkademy.ankasa.MainActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivitySplashBinding
import com.arkademy.ankasa.login.LoginActivity
import com.arkademy.ankasa.onboard.OnBoardActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler().postDelayed({
            val intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}