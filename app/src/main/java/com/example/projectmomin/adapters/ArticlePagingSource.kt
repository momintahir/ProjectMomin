package com.example.projectmomin.adapters

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import com.example.projectmomin.api.NewsAPI
import com.example.projectmomin.db.NewsDatabase
import com.example.projectmomin.models.Article
import com.example.projectmomin.util.networkBoundResource
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject


class ArticlePagingSource @Inject constructor(
    private val api: NewsAPI,
    private val db:NewsDatabase
) : PagingSource<Int, Article>()
{

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
//            val response = api.getBreakingNews("us",currentLoadingPageKey)
            val response=getArticles("us",currentLoadingPageKey)

            val responseData = mutableListOf<Article>()
            Log.d("My Response",response.toString())
            val myData = response
            responseData.addAll(myData.asLiveData().value?.data!!)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

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


}