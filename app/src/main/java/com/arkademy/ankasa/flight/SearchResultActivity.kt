package com.arkademy.ankasa.flight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.FormProfileActivity
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ActivitySearchResultBinding

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchResultBinding
    private lateinit var recyclerAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_result)
        recyclerAdapter = SearchResultAdapter(arrayListOf(flightModel.firstList), object : SearchResultAdapter.onClickViewListener{
            override fun onClick(string: String) {
                val intent = Intent(this@SearchResultActivity, FormProfileActivity::class.java)
                startActivity(intent)
            }
        })
        binding.rvFlight.adapter = recyclerAdapter
        binding.rvFlight.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }
}