package com.arkademy.ankasa.flight

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.arkademy.ankasa.databinding.ItemRecyclerFacilityBinding
import kotlinx.android.synthetic.main.activity_register.view.*

class FacilityAdapter(val items: List<String>) :
    RecyclerView.Adapter<FacilityAdapter.facilityViewHolder>() {
    class facilityViewHolder(val binding: ItemRecyclerFacilityBinding) :
        RecyclerView.ViewHolder(binding.root)


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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: facilityViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvFacility.text = item
        if (item == "Snack") {
            holder.binding.ivFacility.setImageResource(R.drawable.ic_snack)
            holder.binding.llContainerRecycler.setBackgroundResource(R.drawable.container_facility_snack)
        } else if (item == "Wifi") {
            holder.binding.ivFacility.setImageResource(R.drawable.ic_wifi)
            holder.binding.llContainerRecycler.setBackgroundResource(R.drawable.container_facility_wifi)
        } else if (item == "Toilet") {
            holder.binding.ivFacility.setImageResource(R.drawable.ic_wc)
            holder.binding.llContainerRecycler.setBackgroundResource(R.drawable.container_facility_toilet)
        } else {
            holder.binding.ivFacility.setImageResource(R.drawable.ic_star)
            holder.binding.llContainerRecycler.setBackgroundResource(R.drawable.btn_primary)
        }
    }

    override fun getItemCount(): Int = items.size
}

data class FacilityModel(val facility: String)