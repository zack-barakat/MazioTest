package com.android.maziotest.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(var name: String, var price: Double) : Parcelable
