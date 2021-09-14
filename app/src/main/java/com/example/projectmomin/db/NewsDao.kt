package com.example.projectmomin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectmomin.models.Article
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article):Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): StateFlow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}