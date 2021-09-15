package com.example.projectmomin.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projectmomin.R
import com.example.projectmomin.models.Article
import kotlinx.android.synthetic.main.fragment_web_view.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.projectmomin.ui.MainActivity
import com.example.projectmomin.ui.NewsViewModel


class WebViewFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        viewModel = (activity as MainActivity).viewModel

        val bundle = this.arguments
        if (bundle != null) {
            article = bundle.getParcelable<Parcelable>("article") as Article
            view.webView.loadUrl(article.url)
        }
        var toolbar = activity?.findViewById<Toolbar>(R.id.toolbar_top)
        val tvSave = toolbar?.findViewById<TextView>(R.id.tvSave)
        if (tvSave != null) {
            tvSave.visibility = View.VISIBLE
            tvSave.setOnClickListener {
//                val value=viewModel.saveArticle(article)
//                Log.d("ROOMDB","saved successfully $value")
            }
        }
        return view
    }
}