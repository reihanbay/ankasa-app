package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.profile.FormProfileActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivitySearchResultBinding
import com.arkademy.ankasa.utils.api.ApiClient
import com.arkademy.ankasa.utils.api.services.flightApiService
import com.arkademy.ankasa.utils.parcelize.SearchDataParcelize
import retrofit2.create

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var recyclerAdapter: SearchResultAdapter
    private lateinit var viewModel : SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)

        val service = ApiClient.getApiClientToken(this)?.create(flightApiService::class.java)
        viewModel = ViewModelProvider(this).get(SearchResultViewModel::class.java)
        if (service != null) {
            viewModel.setFlightService(service)
        }

        var dataSearch = intent.getParcelableExtra<SearchDataParcelize>("search")
        binding.tvDeparture.text = dataSearch.departure
        binding.tvCityOrigin.text = dataSearch.cityOrigin
        binding.tvCityDestination.text = dataSearch.cityDestination
        binding.tvCountryOrigin.text = dataSearch.countryOrigin
        binding.tvCountryDestination.text = dataSearch.countryDestination
        binding.tvValuePassenger.text = "${dataSearch.child} Child ${dataSearch.adults} Adults"
        binding.tvValueClass.text = dataSearch.classFlight
//        val child = 1
//        val adults = 1
        val child = dataSearch.child.toInt()
        val adults = dataSearch.adults.toInt()
        viewModel.callFlightService(dataSearch.cityOrigin, dataSearch.cityDestination,dataSearch.departure,dataSearch.classFlight)
//        binding.tvCountFlight.text= recyclerAdapter.itemCount.toString() + "Flight Found"
        subscribeLiveData()
        setRecyclerView(child, adults)

    }

    private fun setRecyclerView(child: Int, adults: Int){
        recyclerAdapter = SearchResultAdapter(child, adults, arrayListOf(), object : SearchResultAdapter.onClickViewListener{
            override fun onClick(string: String) {
                val intent = Intent(this@SearchResultActivity, FlightDetailActivity::class.java)
                startActivity(intent)
            }
        })

        binding.rvFlight.adapter = recyclerAdapter
        binding.rvFlight.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    private fun subscribeLiveData() {
        viewModel.flightLiveData.observe(this, Observer {
            (binding.rvFlight.adapter as SearchResultAdapter).addList(it)
        })
    }
}