package com.arkademy.ankasa.dashboard.booking

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class BookingDetailModel(
    val class_airlines: String,
    val code: String,
    val fullname: String,
    val image: String,
    val init_destination: String,
    val init_origin: String,
    val status: String,
    val terminal: String,
    val time_departure: String,
    val time_from: String
)