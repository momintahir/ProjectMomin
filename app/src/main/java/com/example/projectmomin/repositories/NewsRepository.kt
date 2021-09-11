package com.example.projectmomin.repositories

import com.example.projectmomin.api.RetrofitInstance
import com.example.projectmomin.db.NewsDatabase

class NewsRepository(
    val db: NewsDatabase
) {
    suspend fun getBreakingNews() =
        RetrofitInstance.api.getBreakingNews("us")
}