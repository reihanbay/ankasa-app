package com.arkademy.ankasa.dashboard.booking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.item_booking_ticket.view.*

class BookingAdapter(private val bookingList: ArrayList<BookingModel>): RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {
    class BookingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(list: BookingModel) {
            with(itemView) {
                tv_booking_date.text = list.date
                tv_booking_from.text = list.from
                tv_booking_to.text = list.to
                tv_status.text = list.status
                tv_maskapai.text = list.maskapai
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_booking_ticket, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        holder.bind(bookingList[position])
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }
}