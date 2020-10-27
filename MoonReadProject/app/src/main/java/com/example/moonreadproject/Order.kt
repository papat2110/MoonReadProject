package com.example.moonreadproject

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Order (
    @Expose
    @SerializedName("customer_id") val customer_id: Int,

    @Expose
    @SerializedName("total_price") val total_price: String,

    @Expose
    @SerializedName("order_date") val order_date: Int,

    @Expose
    @SerializedName("address") val address: Int,

    @Expose
    @SerializedName("status") val status: Int)
{}