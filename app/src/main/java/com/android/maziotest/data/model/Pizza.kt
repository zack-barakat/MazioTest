package com.android.maziotest.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pizza(var name: String, var price: Double) : Parcelable {
    fun toPizzaSelection(): PizzaSelection {
        return PizzaSelection(name, price, false)
    }
}

@Parcelize
data class PizzaSelection(var name: String, var price: Double, var selected: Boolean) : Parcelable {
    fun toPizza(): Pizza {
        return Pizza(name, price)
    }
}
