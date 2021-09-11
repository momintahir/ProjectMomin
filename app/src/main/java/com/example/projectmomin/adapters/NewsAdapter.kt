package com.example.projectmomin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmomin.R
import com.example.projectmomin.models.Article
import kotlinx.android.synthetic.main.item_article.view.*

internal class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            tvTitle.text = article.title
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
        }


    }

    override fun getItemCount() = differ.currentList.size
}
