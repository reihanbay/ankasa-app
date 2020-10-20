package com.arkademy.ankasa.dashboard.booking

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.item_booking_ticket.view.*

class BookingUserByIdAdapter(val listener: (BookingUserByIdModel)-> Unit): RecyclerView.Adapter<BookingUserByIdAdapter.BookingUserViewHolder>() {

    var bookingUser = ArrayList<BookingUserByIdModel>()

    class BookingUserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(list: BookingUserByIdModel, listener: (BookingUserByIdModel) -> Unit) {
            with(itemView) {
                tv_booking_date.text = list.time_departure
                tv_booking_from.text = list.init_origin
                tv_booking_to.text = list.init_destination
                tv_maskapai.text = list.name_airlines
                tv_kode.text = list.code
                tv_departure_time.text = list.time_from
                if (list.status == "Eticket Issued") {
                    tv_status.text = "Eticket Issued"
                    tv_status.setBackgroundColor(Color.parseColor("#4FCF4D"))
                } else {
                    tv_status.text = list.status
                }
                setOnClickListener { listener(list) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingUserViewHolder {
        return BookingUserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_booking_ticket, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookingUserViewHolder, position: Int) {
        holder.bind(bookingUser[position], listener)
    }

    override fun getItemCount(): Int {
        return bookingUser.size
    }

    fun setData(newBookingUser: ArrayList<BookingUserByIdModel>) {
        bookingUser = newBookingUser
        notifyDataSetChanged()
    }
}