package com.example.repotrends

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.repotrends.adapter.GitHubRepoAdapter
import com.example.repotrends.viewmodels.GitHubViewModel
import com.example.repotrends.viewmodels.GitHubViewModelFactory
import com.example.repotrends.api.ApiService

class MainActivity : AppCompatActivity() {
    private val viewModel: GitHubViewModel by viewModels { GitHubViewModelFactory(ApiService.apiService) }
    private lateinit var adapter: GitHubRepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searchView: SearchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRepos(newText.orEmpty())
                return true
            }
        })
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        adapter = GitHubRepoAdapter(mutableListOf(), object : GitHubRepoAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                handleItemClick(position)
            }
        })

        recyclerView.adapter = adapter

        viewModel.reposLiveData.observe(this, { repos ->
            adapter.setData(repos)
        })


        viewModel.loadTrendingRepos()
    }

    private fun handleItemClick(position: Int) {
        val clickedRepo = adapter.data[position]
        Toast.makeText(this, "Clicked: ${clickedRepo.name}", Toast.LENGTH_SHORT).show()
        adapter.toggleItemSelection(position)
    }
}


