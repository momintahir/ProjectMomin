package com.example.projectmomin.api

import com.example.projectmomin.models.NewsResponse
import com.example.projectmomin.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("q")
        q: String,
        @Query("apiKey")
        apiKey: String = API_KEY

    ): NewsResponse

}