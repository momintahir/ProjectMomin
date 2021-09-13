package com.example.projectmomin.ui.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmomin.R
import com.example.projectmomin.adapters.NewsAdapter
import com.example.projectmomin.models.Article
import com.example.projectmomin.ui.MainActivity
import com.example.projectmomin.ui.NewsViewModel
import com.example.projectmomin.ui.NewsViewModelProviderFactory
import com.example.projectmomin.util.Resource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.projectmomin.db.NewsDatabase
import com.example.projectmomin.repositories.NewsRepository
import kotlinx.android.synthetic.main.fragment_breaking_news.view.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class BreakingNewsFragment : Fragment() {

    lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_breaking_news, container, false)
        viewModel = (activity as MainActivity).viewModel
//        viewModel.getBreakingNews()




        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Resource.Loading -> {
                }
            }

        })

        setupRecyclerView(view)

        lifecycleScope.launch {
            viewModel.listData.collect {myData->
                newsAdapter.submitData(myData)
            }
        }
        newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, article: Article) {
                val bundle = Bundle()
                bundle.putParcelable("article", article)
                Navigation.findNavController(view)
                    .navigate(R.id.action_breakingNewsFragment_to_webViewFragment, bundle)

            }
        })
        return view;
    }

    private fun setupRecyclerView(view: View) {
        newsAdapter = NewsAdapter()
        view.recyclerView.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}