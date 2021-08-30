package com.example.retrofit.data.retrofit.wiki

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WikiAPI {
    private const val URL =
        "https://en.wikipedia.org/w/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: WikiService = retrofit.create(WikiService::class.java)
}