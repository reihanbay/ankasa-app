package com.arkademy.ankasa.dashboard.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setTopDestinationRecyclerViw()
    }

    private fun setRecyclerView() {
        rv_trending_destination.adapter = TrendingAdapter(TrendingModel.getAllDestination())
        rv_trending_destination.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
    }

    private fun setTopDestinationRecyclerViw() {
        rv_top_destination.adapter = TopAdapter(TopModel.getDestination())
        rv_top_destination.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
     }
}