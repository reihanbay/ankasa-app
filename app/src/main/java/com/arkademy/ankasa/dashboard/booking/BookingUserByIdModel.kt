package com.arkademy.ankasa.dashboard.booking

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingUserByIdModel(
    val code: String,
    val init_destination: String,
    val init_origin: String,
    val name_airlines: String,
    val status: String,
    val time_departure: String,
    val time_from: String,
    val total_price: String
): Parcelable