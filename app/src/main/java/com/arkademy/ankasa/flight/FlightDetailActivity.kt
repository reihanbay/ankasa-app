package com.arkademy.ankasa.flight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivityFlightDetailBinding
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.flightApiService
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import com.bumptech.glide.Glide

class FlightDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFlightDetailBinding
    private lateinit var viewModel: FlightDetailViewModel
    private lateinit var sharedPref : PreferenceHelper
    var idAirlines : Int? = 0
    var totalPrice : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_flight_detail)
        viewModel = ViewModelProvider(this).get(FlightDetailViewModel::class.java)
        val service = ApiClient.getApiClientToken(this)?.create(flightApiService::class.java)
        if (service != null) {
            viewModel.setGetService(service)
        }

        sharedPref = PreferenceHelper(this)
        val idFlight = intent.getStringExtra("idFlight")
        subsribeLiveData()
        viewModel.callGetApi(idFlight.toInt())
        val idUser = sharedPref.getString(Constants.KEY_ID)
        Log.d("checking", " $idUser" )
//        binding.btnBooking.setOnClickListener {
//            viewModel.callPostApi()
//        }
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
            Log.d("checking" , "${idAirlines} ${totalPrice}")
        })
    }

}