package com.arkademy.ankasa.flight

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ItemRecyclerFlightBinding

class SearchResultAdapter(var items: ArrayList<flightModel>, val listener: onClickViewListener) :
    RecyclerView.Adapter<SearchResultAdapter.searchResultViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchResultViewHolder {
        return searchResultViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recycler_flight,
            parent,
            false
            )
        )
    }

    interface onClickViewListener {
        fun onClick(string: String)
    }

    class searchResultViewHolder(val binding: ItemRecyclerFlightBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: searchResultViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvInitFrom.text = item.from
        holder.binding.tvInitDestination.text = item.destination
        holder.binding.tvTimeFrom.text = item.timeFrom
        holder.binding.tvTimeDestination.text = item.timeDestination
        holder.binding.tvValueTerminal.text = item.terminal
        holder.binding.tvValueGate.text = item.gate
        holder.binding.tvPlaneFlight.text = item.flight
        holder.binding.tvPrice.text = "$ ${(item.priceAdults * item.priceChild).toDouble()}"
        holder.binding.containerRecycler.setOnClickListener {
            listener.onClick(item.flight)
        }

    }

    override fun getItemCount(): Int = items.size
}