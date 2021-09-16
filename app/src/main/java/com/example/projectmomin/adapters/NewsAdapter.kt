package com.example.projectmomin.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmomin.R
import com.example.projectmomin.models.Article
import kotlinx.android.synthetic.main.item_article.view.*

internal class NewsAdapter : PagingDataAdapter<Article, NewsAdapter.MyViewHolder>(differCallback) {
    private lateinit var onItemClickListener: OnItemClickListener

    object differCallback : DiffUtil.ItemCallback<Article>() {
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
        holder.itemView.apply {
            tvTitle.text = getItem(position)!!.title
            tvSource.text = getItem(position)!!.source!!.name
            tvPublishedAt.text = getItem(position)!!.publishedAt
            tvDescription.text = getItem(position)!!.description
            Glide.with(this).load(getItem(position)!!.urlToImage).into(ivArticleImage)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, getItem(position)!!)
        }

    }



    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, article: Article)
    }
}
