package com.example.projectmomin.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmomin.R
import com.example.projectmomin.adapters.NewsAdapter
import com.example.projectmomin.ui.MainActivity
import com.example.projectmomin.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_breaking_news.view.*


class SavedNewsFragment : Fragment() {
    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_saved_news, container, false)
        viewModel = (activity as MainActivity).viewModel
//
//        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer {
//            newsAdapter.differ.submitList(it)
//        })
        setupRecyclerView(view)
        return view
    }
    private fun setupRecyclerView(view: View) {
        newsAdapter = NewsAdapter()
        view.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}