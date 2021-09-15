package com.example.projectmomin.repositories

import com.example.projectmomin.api.NewsAPI
import javax.inject.Inject


class NewsRepository @Inject constructor(
//    val db: NewsDatabase,
    private val api: NewsAPI
) {
//    suspend fun getBreakingNews() =
//        RetrofitInstance.api.getBreakingNews("us")

    suspend fun getSearchNews(query: String) =
        api.getSearchNews(query)

//    suspend fun upsert(article: Article) = db.getNewsDao().upsert(article)

//    fun getSavedNews()=db.getNewsDao().getAllArticles()
}