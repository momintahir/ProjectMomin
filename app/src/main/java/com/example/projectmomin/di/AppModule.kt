package com.example.projectmomin.di

import androidx.paging.PagingSource
import com.example.projectmomin.adapters.ArticlePagingSource
import com.example.projectmomin.api.NewsAPI
import com.example.projectmomin.repositories.NewsRepository
import com.example.projectmomin.util.Constants
import com.example.projectmomin.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitAPI(): NewsAPI =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPI::class.java)
//    val logging = HttpLoggingInterceptor()
//    val client = OkHttpClient.Builder()
//        .addInterceptor(logging)
//        .build()


    @Singleton
    @Provides
    fun providesRepository(api: NewsAPI): NewsRepository = NewsRepository(api)

    @Singleton
    @Provides
    fun providesPagging(api: NewsAPI): ArticlePagingSource = ArticlePagingSource(api)


}