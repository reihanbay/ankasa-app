package com.arkademy.ankasa.booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.arkademy.ankasa.R
import com.arkademy.ankasa.dashboard.booking.BookingFragment
import com.arkademy.ankasa.dashboard.booking.BookingUserByIdModel
import com.arkademy.ankasa.databinding.ActivityDetailBookingBinding

class DetailBookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBookingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_booking)

        val data = intent.getParcelableExtra(BookingFragment.GET_INTENT) as BookingUserByIdModel
        binding.apply {
            tvInitFrom.text = data.init_origin
            tvInitDestination.text = data.init_destination
            tvStatus.text = data.status
            tvCode.text = data.code
            tvDeparture.text = data.time_departure
            tvTime.text = data.time_from
        }
    }
}