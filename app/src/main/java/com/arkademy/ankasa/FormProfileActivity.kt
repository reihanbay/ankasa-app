package com.arkademy.ankasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_form_profile.*

class FormProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_profile)
        val city= arrayOf("Medan", "Jakarta", "Bali", "Sulawesi")


        val adapter = ArrayAdapter(this@FormProfileActivity, android.R.layout.simple_spinner_dropdown_item, city)
        et_profile_city.setAdapter(adapter)
    }
}