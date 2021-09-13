package com.example.projectmomin.repositories

import com.example.projectmomin.api.RetrofitInstance
import com.example.projectmomin.db.NewsDatabase
import com.example.projectmomin.models.Article

class NewsRepository(
    val db: NewsDatabase
) {
//    suspend fun getBreakingNews() =
//        RetrofitInstance.api.getBreakingNews("us")

    suspend fun getSearchNews(query: String) =
        RetrofitInstance.api.getSearchNews(query)

    suspend fun upsert(article: Article) = db.getNewsDao().upsert(article)

    fun getSavedNews()=db.getNewsDao().getAllArticles()
}