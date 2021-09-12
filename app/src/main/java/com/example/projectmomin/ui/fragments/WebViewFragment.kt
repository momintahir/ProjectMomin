package com.example.projectmomin.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmomin.R
import com.example.projectmomin.models.Article
import kotlinx.android.synthetic.main.fragment_web_view.view.*


class WebViewFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        val bundle = this.arguments
        if (bundle != null) {
            val article: Article = bundle.getParcelable<Parcelable>("article") as Article
            view.webView.loadUrl(article.url)
        }
        return view
    }
}