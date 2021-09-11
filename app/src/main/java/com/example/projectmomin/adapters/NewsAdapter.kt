package com.example.projectmomin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmomin.R
import com.example.projectmomin.models.Article

internal class NewsAdapter(private val list: ArrayList<Article>, val context: Context) :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tvTitle)
        var source: TextView = view.findViewById(R.id.tvSource)
        var publishedAt: TextView = view.findViewById(R.id.tvPublishedAt)
        var image: ImageView = view.findViewById(R.id.ivArticleImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.source.text = list[position].source.name
        holder.publishedAt.text = list[position].publishedAt
        Glide.with(context).load(list[position].urlToImage).into(holder.image)

    }

    override fun getItemCount() = list.size
}
