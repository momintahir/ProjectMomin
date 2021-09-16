package com.example.projectmomin.repositories

import androidx.room.withTransaction
import com.example.projectmomin.api.NewsAPI
import com.example.projectmomin.db.NewsDatabase
import com.example.projectmomin.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val db: NewsDatabase,
    private val api: NewsAPI

) {
//    suspend fun getBreakingNews() =
//        RetrofitInstance.api.getBreakingNews("us")

    suspend fun getSearchNews(query: String) =
        api.getSearchNews(query)

    fun getArticles(query: String,currentLoadingPage:Int)= networkBoundResource(
        query = { db.getNewsDao().getAllArticles()},
        fetch = {
            delay(2000)
            api.getBreakingNews(query,currentLoadingPage) },
        saveFetchResult = {newsResponse ->
            db.withTransaction {
                db.getNewsDao().deleteAllArticles()
                db.getNewsDao().insertArticles(newsResponse.articles)
            }
        }
    )

//    suspend fun upsert(article: Article) = db.getNewsDao().upsert(article)

//    fun getSavedNews()=db.getNewsDao().getAllArticles()
}