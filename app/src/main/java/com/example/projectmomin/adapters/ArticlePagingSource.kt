package com.example.projectmomin.adapters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.projectmomin.api.RetrofitInstance
import com.example.projectmomin.models.Article
import java.lang.Exception

class ArticlePagingSource:PagingSource<Int,Article> () {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = RetrofitInstance.api.getBreakingNews("us",currentLoadingPageKey)
            val responseData = mutableListOf<Article>()
            val data = response.articles
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1))
        } catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}