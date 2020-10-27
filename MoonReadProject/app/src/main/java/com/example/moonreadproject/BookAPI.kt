package com.example.moonreadproject

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface BookAPI {
    @GET("allbook")
    fun retrieveBook(): Call<List<Book>>

    companion object{
        fun create(): BookAPI{
            val bookClient: BookAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookAPI::class.java)
            return bookClient
        }
    }

    @GET("book/{keyword}")
    fun searchbook(
        @Path("keyword") book_name: String
    ): Call<List<Book>>
}