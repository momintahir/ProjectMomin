package com.example.projectmomin.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmomin.R
import com.example.projectmomin.adapters.NewsAdapter
import com.example.projectmomin.adapters.SearchNewsAdapter
import com.example.projectmomin.ui.MainActivity
import com.example.projectmomin.ui.NewsViewModel
import com.example.projectmomin.util.Resource
import kotlinx.android.synthetic.main.fragment_search_news.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class SearchNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    internal lateinit var newsAdapter: SearchNewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_news, container, false)
        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView(view)


        view.etSearchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.viewModelScope.launch {
                    delay(2000)
                    viewModel.getSearchedNews(s.toString())
                }
            }
        })


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.searchNews.collect { response->
                when (response) {
                    is Resource.Success -> {
                        response.data?.let { newsResponse ->
                            newsAdapter.differ.submitList(newsResponse.articles)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Toast.makeText(
                                activity,
                                "An error occured: $message",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                    is Resource.Loading -> {
                    }
                }
            }
        }

        return view
    }

    private fun setupRecyclerView(view: View) {
        newsAdapter = SearchNewsAdapter()
        view.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}