package com.arkademy.ankasa.dashboard.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.item_trending_destination.*

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private lateinit var viewModel: ExploreViewModel
    private lateinit var adapterTrending: TrendingAdapter
    private lateinit var adapterTopDestinantion: TopDestinationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterTrending = TrendingAdapter()
        adapterTopDestinantion = TopDestinationAdapter()
        setRecyclerView()
        setTopDestinationRecyclerView()

        viewModel = ViewModelProvider(this)[ExploreViewModel::class.java]
        viewModel.getTrendingDestination()
        viewModel.getTopDestination()

        viewModel.responseTrendingDestination.observe(this, { response ->
            val listTrending: List<TrendingModel> = response.body()?.data!!.map {
                TrendingModel(it.city, it.country, it.id_routes, it.image)
            }
            adapterTrending.setData(listTrending as ArrayList<TrendingModel>)
        })

        viewModel.responseTopDestination.observe(this, { response ->
            val listTopDestination: List<TopDestinationModel> = response.body()!!.data.map {
                TopDestinationModel(it.city, it.country, it.id_routes, it.image)
            }
            adapterTopDestinantion.setData(listTopDestination as ArrayList<TopDestinationModel>)
        })
    }

    private fun setRecyclerView() {
        rv_trending_destination.adapter = adapterTrending
        rv_trending_destination.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }

    private fun setTopDestinationRecyclerView() {
        rv_top_destination.adapter = adapterTopDestinantion
        rv_top_destination.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }
}