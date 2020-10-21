package com.arkademy.ankasa.dashboard.booking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arkademy.ankasa.R
import com.arkademy.ankasa.booking.DetailBookingActivity
import com.arkademy.ankasa.utils.sharedpreferences.Constants
import com.arkademy.ankasa.utils.sharedpreferences.PreferenceHelper
import kotlinx.android.synthetic.main.fragment_booking.*

class BookingFragment : Fragment() {

    private lateinit var sharedpref: PreferenceHelper
    private lateinit var viewModel: BookingUserViewModel
    private lateinit var adapter: BookingUserByIdAdapter

    companion object {
        const val GET_INTENT = "0000"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedpref = PreferenceHelper(context!!)
        adapter = BookingUserByIdAdapter{
            val intent = Intent(activity, DetailBookingActivity::class.java)
            intent.putExtra(GET_INTENT, it)
            startActivity(intent)
        }
        viewModel = BookingUserViewModel()
        setRecylerView()
        val idUser = sharedpref.getString(Constants.KEY_ID)!!.toInt()
        val bookingId = sharedpref.getString(Constants.PREF_CUSTOMER)
        Log.d("bookingId", bookingId.toString())

        viewModel.getBookingUserById(idUser)
        viewModel.responseBookingUserById.observe(this, { response ->
            val listBookingUser: List<BookingUserByIdModel>? = response.body()?.data?.map {
                BookingUserByIdModel(it.code, it.init_destination, it.init_origin, it.name_airlines, it.status, it.time_departure, it.time_from, it.total_price)
            }
            adapter.setData(listBookingUser as ArrayList<BookingUserByIdModel>)
        })

//        viewModel.getBookingDetail(5)
//        viewModel.responseBookingDetail.observe(this, {response->
//            val bookingDetail = response.body()?.data
//            tv_passenger.text = bookingDetail?.fullname
//        })
    }

    private fun setRecylerView() {
        rv_booking.adapter = adapter
        rv_booking.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }
}