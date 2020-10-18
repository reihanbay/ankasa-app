package com.arkademy.ankasa.dashboard.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_top_destination.view.*

class TopAdapter(val topDestination: ArrayList<TopModel>): RecyclerView.Adapter<TopAdapter.TopViewHolder>() {
    class TopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(list: TopModel) {
            with(itemView){
                Glide.with(itemView)
                    .load(list.image)
                    .into(iv_top_destination)
                tv_top_destination.text = list.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        return TopViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_destination, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
       holder.bind(topDestination[position])
    }

    override fun getItemCount(): Int {
        return topDestination.size
    }
}