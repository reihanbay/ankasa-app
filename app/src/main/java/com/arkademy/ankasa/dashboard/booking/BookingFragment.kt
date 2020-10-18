package com.arkademy.ankasa.dashboard.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import kotlinx.android.synthetic.main.fragment_booking.*

class BookingFragment : Fragment() {

    private lateinit var adapter: BookingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BookingModel.getBookingList()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        rv_booking.adapter = BookingAdapter(BookingModel.getBookingList())
        rv_booking.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }
}