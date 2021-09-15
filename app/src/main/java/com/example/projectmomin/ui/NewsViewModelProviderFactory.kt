package com.example.projectmomin.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectmomin.adapters.ArticlePagingSource
import com.example.projectmomin.repositories.NewsRepository

class NewsViewModelProviderFactory(
    val app: Application,
    private val newsRepository: NewsRepository,
    private val articlePagingSource: ArticlePagingSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app, newsRepository,articlePagingSource) as T
    }
}