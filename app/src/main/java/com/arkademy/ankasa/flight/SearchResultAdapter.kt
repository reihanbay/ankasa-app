package com.arkademy.ankasa.flight

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ItemRecyclerFlightBinding
import com.arkademy.ankasa.utils.api.model.getFlightModel

class SearchResultAdapter(val child: Int, val adults: Int, var items: ArrayList<getFlightModel>, val listener: onClickViewListener) :
    RecyclerView.Adapter<SearchResultAdapter.searchResultViewHolder>() {

    fun addList(list: List<getFlightModel>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }
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
        fun onClick(id: Int)
    }

    class searchResultViewHolder(val binding: ItemRecyclerFlightBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: searchResultViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvInitFrom.text = item.initOrigin
        holder.binding.tvInitDestination.text = item.initDestination
        holder.binding.tvTimeFrom.text = item.timeFrom
        holder.binding.tvTimeDestination.text = item.timeDestination
        holder.binding.tvValueTerminal.text = item.terminal

//        val gateSplit = item.codeFlight.split("")
//        val gateCount = gateSplit.size
//        var arr : ArrayList<String>
//        for (gateCount)
        holder.binding.tvValueGate.text = item.codeFlight
        holder.binding.tvPlaneFlight.text = item.nameFlight
        holder.binding.tvPrice.text = "$ ${((adults * item.adult.toInt()) + (child * item.child.toInt())).toDouble()}"
        holder.binding.containerRecycler.setOnClickListener {
            listener.onClick(item.idAirlines)
        }

    }

    override fun getItemCount(): Int = items.size
}