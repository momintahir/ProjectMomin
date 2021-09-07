package com.example.projectmomin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectmomin.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class NewsDatabase: RoomDatabase() {


}