package com.arkademy.ankasa.utils.parcelize

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchDataParcelize(
    val cityOrigin: String,
    val cityDestination: String,
    val countryOrigin: String,
    val countryDestination: String,
    val departure: String,
    val child: String,
    val adults: String,
    val classFlight: String
) : Parcelable