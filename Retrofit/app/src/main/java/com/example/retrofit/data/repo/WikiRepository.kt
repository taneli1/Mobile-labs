package com.example.retrofit.data.repo

import com.example.retrofit.data.retrofit.wiki.WikiAPI

class WikiRepository {
    private val service = WikiAPI.service

    suspend fun hitCount(query: String) = service.hitCount(query)
}