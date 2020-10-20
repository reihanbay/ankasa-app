package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arkademy.ankasa.R
import com.arkademy.ankasa.profile.FormProfileActivity
import kotlinx.android.synthetic.main.activity_search_flight.*

class SearchFlightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_flight)

        btn_search_flight.setOnClickListener{
            startActivity(Intent(this, FormProfileActivity::class.java))
        }
    }
}