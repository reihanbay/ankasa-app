package com.arkademy.ankasa.dashboard.explore

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationModel(
    val city: String,
    val country: String,
    val id_routes: Int,
    val image: String
): Parcelable