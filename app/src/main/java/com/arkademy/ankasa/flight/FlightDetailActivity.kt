package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.MainActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.dashboard.booking.BookingFragment
import com.arkademy.ankasa.databinding.ActivityFlightDetailBinding
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.BookingService
import com.arkademy.ankasa.utils.api.services.flightApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import com.bumptech.glide.Glide

class FlightDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlightDetailBinding
    private lateinit var viewModel: FlightDetailViewModel
    private lateinit var sharedPref : PreferenceHelper
    private lateinit var recyclerFacility : FacilityAdapter
    var intentMain = MainActivity()
    var idAirlines : Int? = null
    var totalPrice : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_flight_detail)
        viewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)
        val serviceGet = ApiClient.getApiClientToken(this)?.create(flightApiService::class.java)
        val servicePost = ApiClient.getApiClientToken(this)?.create(BookingService::class.java)
        if (serviceGet != null) {
            viewModel.setGetService(serviceGet)
        }
        if (servicePost != null) {
            viewModel.setPostService(servicePost)
        }

        sharedPref = PreferenceHelper(this)
        val idFlight = intent.getStringExtra("idFlight")
        subsribeLiveData()
        viewModel.callGetApi(idFlight.toInt())
        val idUser = sharedPref.getString(Constants.KEY_ID)
        if (idUser != null) {
            postBook(idUser.toInt())
        }

    }

    private fun subsribeLiveData(){
        viewModel.detailLiveData.observe(this, Observer {
            val child = intent.getIntExtra("child",0)
            val adults = intent.getIntExtra("adults",0)
            Glide.with(this@FlightDetailActivity)
                .load("http://34.227.91.246:8080/uploads/${it.data.image}")
                .placeholder(R.drawable.ic_booking_plane)
                .into(binding.ivFlight)
            binding.tvInitFrom.text = it.data.initOrigin
            binding.tvInitDestination.text = it.data.initDestination
            binding.tvTimeFrom.text = it.data.timeFrom
            binding.tvTimeDestination.text = it.data.timeDestination
            binding.tvCode.text = it.data.codeFlight
            binding.tvValueClass.text = it.data.classFlight
            binding.tvValueTerminal.text = it.data.terminal
            val gate = it.data.codeFlight.split("")
            binding.tvValueGate.text = "${gate[4]}${gate[5]}${gate[6]}"
            binding.tvValueChild.text = child.toString()
            binding.tvValueAdults.text = adults.toString()
            binding.tvPriceTotal.text = "$ " + ((child * it.data.child) + (adults * it.data.adult)).toDouble().toString()
            idAirlines = it.data.idAirlines
            totalPrice = (child * it.data.child) + (adults * it.data.adult)

            var data = it.data.facilities.split(", ")
            recyclerFacility = FacilityAdapter(data)
            binding.rvFacility.adapter = recyclerFacility
            binding.rvFacility.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        })
    }

    private fun postBook(id: Int) {
        binding.btnBooking.setOnClickListener {
            viewModel.callPostApi(idAirlines!!, id, totalPrice!!,"Eticket Issued")
            val intent = Intent(this, intentMain::class.java)
            intent.putExtra("code", 1)
            startActivity(intent)
        }
    }

}