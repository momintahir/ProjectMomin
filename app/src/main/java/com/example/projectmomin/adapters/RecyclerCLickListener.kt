package com.example.projectmomin.adapters

import com.example.projectmomin.models.Article

interface RecyclerCLickListener {

    fun onItemClick(position:Int,article: Article)
}