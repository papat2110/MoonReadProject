package com.example.moonreadproject

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OrderAPI {

    @FormUrlEncoded
    @POST("createorder")
    fun insertOrder(
        @Field("customer_id") customer_id: Int,
        @Field("total_price") total_price: Int,
        @Field("order_date") order_date: String,
        @Field("address") address: String,
        @Field("status") status: String
    ): Call<Order>
}