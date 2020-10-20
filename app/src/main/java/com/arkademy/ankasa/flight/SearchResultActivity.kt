package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.FormProfileActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivitySearchResultBinding
import com.arkademy.ankasa.utils.parcelize.SearchDataParcelize

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var recyclerAdapter: SearchResultAdapter
    private lateinit var viewModel : SearchResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)

//        var dataSearch = intent.getParcelableExtra<SearchDataParcelize>("search")
//        binding.tvDeparture.text = dataSearch.departure
//        binding.tvCityOrigin.text = dataSearch.cityOrigin
//        binding.tvCityDestination.text = dataSearch.cityDestination
//        binding.tvCountryOrigin.text = dataSearch.countryOrigin
//        binding.tvCountryDestination.text = dataSearch.countryDestination
//        binding.tvValuePassenger.text = "${dataSearch.child} Child ${dataSearch.adults} Adults"
//        binding.tvValueClass.text = dataSearch.classFlight
        val child = 1
        val adults = 1
//        val child = dataSearch.child.toInt()
//        val adults = dataSearch.adults.toInt()
        recyclerAdapter = SearchResultAdapter(child, adults, arrayListOf(flightModel.firstList), object : SearchResultAdapter.onClickViewListener{
            override fun onClick(string: String) {
                val intent = Intent(this@SearchResultActivity, FormProfileActivity::class.java)
                startActivity(intent)
            }
        })
        binding.rvFlight.adapter = recyclerAdapter
        binding.rvFlight.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}