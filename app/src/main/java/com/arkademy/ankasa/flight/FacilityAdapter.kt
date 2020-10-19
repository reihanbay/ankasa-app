package com.arkademy.ankasa.flight

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ItemRecyclerFacilityBinding

class FacilityAdapter : RecyclerView.Adapter<FacilityAdapter.facilityViewHolder>() {
    class facilityViewHolder(val binding: ItemRecyclerFacilityBinding) : RecyclerView.ViewHolder(binding.root)

   
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): facilityViewHolder {
        return facilityViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recycler_facility,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: facilityViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}