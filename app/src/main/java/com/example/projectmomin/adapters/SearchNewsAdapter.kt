package com.example.projectmomin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectmomin.R
import com.example.projectmomin.models.Article
import kotlinx.android.synthetic.main.item_article.view.*

internal class SearchNewsAdapter : RecyclerView.Adapter<SearchNewsAdapter.MyViewHolder>() {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchNewsAdapter.MyViewHolder, position: Int) {
        val article=differ.currentList[position]
        holder.itemView.apply {
            tvTitle.text = article.title
            tvSource.text = article.source.name
            tvPublishedAt.text = article.publishedAt
            tvDescription.text = article.description
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, article)
        }

    }



    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, article: Article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
