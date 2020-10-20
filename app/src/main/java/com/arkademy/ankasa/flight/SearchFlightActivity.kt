package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.arkademy.ankasa.R
import com.arkademy.ankasa.dashboard.explore.ExploreFragment
import com.arkademy.ankasa.dashboard.explore.LocationModel

import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.arkademy.ankasa.databinding.ActivitySearchFlightBinding
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.CityService
import com.arkademy.ankasa.utils.parcelize.SearchDataParcelize

import com.arkademy.ankasa.profile.FormProfileActivity
import kotlinx.android.synthetic.main.activity_search_flight.*

class SearchFlightActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchFlightBinding
    private lateinit var viewModel: SearchFlightViewModel
    var selectedSpinnerOrigin: String? = null
    var selectedSpinnerDestination: String? = null
    var selectedRadio: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_flight)

        val dataIntent = intent.getStringExtra(ExploreFragment.INTENT_KEY)
        var spinerData: String? = binding.editSpinner2.text.toString()
        spinerData = dataIntent

        btn_search_flight.setOnClickListener {
            startActivity(Intent(this, FormProfileActivity::class.java))

            viewModel = ViewModelProvider(this).get(SearchFlightViewModel::class.java)
            val service = ApiClient.getApiClientToken(this)?.create(CityService::class.java)
            if (service != null) {
                viewModel.setSpinnerApi(service)

            }

            subscribeLiveData()
            viewModel.callSpinnerApi()

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val checkRadio = checkedId
                if (checkRadio == binding.radioBusiness.id) {
                    selectedRadio = binding.radioBusiness.text.toString()
                    Toast.makeText(this, selectedRadio, Toast.LENGTH_SHORT).show()
                } else if (checkRadio == binding.radioEconomy.id) {
                    selectedRadio = binding.radioEconomy.text.toString()
                    Toast.makeText(this, selectedRadio, Toast.LENGTH_SHORT).show()

                } else if (checkRadio == binding.radioFirstClass.id) {
                    selectedRadio = binding.radioFirstClass.text.toString()
                    Toast.makeText(this, selectedRadio, Toast.LENGTH_SHORT).show()

                } else {
                    selectedRadio = null.toString()
                    Toast.makeText(this, selectedRadio, Toast.LENGTH_SHORT).show()

                }
            }

            binding.btnSearchFlight.setOnClickListener {
                val origin = if (selectedSpinnerOrigin.toString() == "null") {
                    binding.editSpinner1.text.toString()
                } else {
                    selectedSpinnerOrigin.toString()
                }
                val destination = if (selectedSpinnerDestination.toString() == "null") {
                    binding.editSpinner2.text.toString()
                } else {
                    selectedSpinnerDestination.toString()
                }
                val countryOrigin = binding.tvCountryOrigin.text.toString()
                val countryDestination = binding.tvCountryDestination.text.toString()
                val departure = binding.etTglBerangkat.text.toString()
                val child = binding.etChild.text.toString()
                val adult = binding.etAdult.text.toString()
                val data = SearchDataParcelize(
                    origin.toString(),
                    destination.toString(),
                    countryOrigin,
                    countryDestination,
                    departure,
                    child,
                    adult,
                    selectedRadio.toString()
                )
                if (departure != "" && child != "" && adult != "" && selectedRadio.toString() != "null") {
                    val intent = Intent(this, SearchResultActivity::class.java)
                    intent.putExtra("search", data)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Please Fill The Filter", Toast.LENGTH_SHORT).show()
                }

                Log.d("checkData", data.toString())
            }

        }
    }

        fun subscribeLiveData() {
            viewModel.spinnerLiveData.observe(this, Observer {
                var spinner1 = binding.editSpinner1
                var spinner2 = binding.editSpinner2
                val adapter = ArrayAdapter<String>(
                    this@SearchFlightActivity,
                    R.layout.support_simple_spinner_dropdown_item,
                    it.map {
                        it.city
                    })
                spinner1.setAdapter(adapter)
                spinner2.setAdapter(adapter)
                val onSelected1 = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedSpinnerOrigin = it[position].city
                    }
                }
                val onSelected2 = object : AdapterView.OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        selectedSpinnerDestination = it[position].city
                    }
                }

                spinner1.setOnItemClickListener(onSelected1)
                spinner2.setOnItemClickListener(onSelected2)
            })
        }

}
