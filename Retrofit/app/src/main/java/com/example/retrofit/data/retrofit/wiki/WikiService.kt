package com.example.retrofit.data.retrofit.wiki

import retrofit2.http.GET
import retrofit2.http.Query

const val DEFAULT_WIKI_PARAMS = "api.php?action=query&format=json&list=search"

interface WikiService {

    @GET(DEFAULT_WIKI_PARAMS)
    suspend fun hitCount(@Query("srsearch") query: String): WikiResponse
}