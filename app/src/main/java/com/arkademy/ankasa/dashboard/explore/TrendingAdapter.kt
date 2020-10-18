package com.arkademy.ankasa.dashboard.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_trending_destination.view.*

class TrendingAdapter(private val listDestination: ArrayList<TrendingModel>): RecyclerView.Adapter<TrendingAdapter.ExploreViewHolder>() {
    class ExploreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(destination: TrendingModel) {
            with(itemView){
                Glide.with(itemView)
                    .load(destination.image)
                    .apply(RequestOptions().override(700, 600))
                    .into(iv_trending_destination)
                tv_trending_city.text = destination.city
                tv_trending_country.text = destination.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreViewHolder {
        return ExploreViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trending_destination, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExploreViewHolder, position: Int) {
        holder.bind(listDestination[position])
    }

    override fun getItemCount(): Int {
        return listDestination.size
    }
}