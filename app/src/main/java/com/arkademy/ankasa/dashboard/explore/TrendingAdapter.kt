package com.arkademy.ankasa.dashboard.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_trending_destination.view.*

class TrendingAdapter: RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var trendingList = ArrayList<TrendingModel>()

    class TrendingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(listTrending: TrendingModel) {
            with(itemView){
                Glide.with(itemView.context)
                    .load("http://34.227.91.246:8080/uploads/" + listTrending.image)
                    .apply(RequestOptions().override(600, 600))
                    .into(iv_trending_destination)
                tv_trending_city.text = listTrending.city
                tv_trending_country.text = listTrending.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_trending_destination, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        holder.bind(trendingList[position])
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    fun setData(newTrendingList: ArrayList<TrendingModel>){
        trendingList = newTrendingList
        notifyDataSetChanged()
    }
}