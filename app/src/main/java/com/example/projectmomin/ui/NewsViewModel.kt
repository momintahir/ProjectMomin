package com.example.projectmomin.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.projectmomin.adapters.ArticlePagingSource
import com.example.projectmomin.models.Article
import com.example.projectmomin.models.NewsResponse
import com.example.projectmomin.repositories.NewsRepository
import com.example.projectmomin.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(
    app: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val searchNews: MutableStateFlow<Resource<NewsResponse>> = MutableStateFlow(Resource.Empty())

    fun getSearchedNews(query: String) = viewModelScope.launch {
        searchNews.value = Resource.Loading()
        val response = newsRepository.getSearchNews(query)
        searchNews.value = Resource.Success(response)
    }

//    fun getBreakingNews() = viewModelScope.launch {
//        breakingNews.postValue(Resource.Loading())
//        try {
//            val response = newsRepository.getBreakingNews()
//            breakingNews.postValue(Resource.Success(response))
//
//        } catch (t: Throwable) {
//            when (t) {
//                is IOException -> breakingNews.postValue(Resource.Error("Network Failure"))
//                else -> breakingNews.postValue(Resource.Error("Conversion Error"))
//            }
//        }
//    }

    val listData = Pager(PagingConfig(pageSize = 6)) {
        ArticlePagingSource()
    }.flow.cachedIn(viewModelScope)

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()
}