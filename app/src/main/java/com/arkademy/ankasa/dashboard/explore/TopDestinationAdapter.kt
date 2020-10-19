package com.arkademy.ankasa.dashboard.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_top_destination.view.*

class TopDestinationAdapter: RecyclerView.Adapter<TopDestinationAdapter.TopDestinationViewHolder>() {

    private var topDestinationList = ArrayList<TopDestinationModel>()

    class TopDestinationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(topDestionation: TopDestinationModel) {
            with(itemView){
                Glide.with(itemView)
                    .load("http://34.227.91.246:8080/uploads/" + topDestionation.image)
                    .apply(RequestOptions().override(400, 400))
                    .into(iv_top_destination)
                tv_top_destination.text = topDestionation.country
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDestinationViewHolder {
        return TopDestinationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_top_destination, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopDestinationViewHolder, position: Int) {
        holder.bind(topDestinationList[position])
    }

    override fun getItemCount(): Int {
        return topDestinationList.size
    }

    fun setData(newTopDestination: ArrayList<TopDestinationModel>) {
        topDestinationList = newTopDestination
        notifyDataSetChanged()
    }
}

