package com.example.projectmomin.db

import androidx.room.*
import com.example.projectmomin.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(article: List<Article>)

    @Query("delete from articles")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}