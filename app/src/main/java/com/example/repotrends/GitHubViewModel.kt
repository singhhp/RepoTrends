package com.example.repotrends.viewmodels

import GitHubApiService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.repotrends.model.GitHubApiResponse
import com.example.repotrends.model.GitHubRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubViewModel(private val apiService: GitHubApiService) : ViewModel() {

    private val _reposLiveData = MutableLiveData<List<GitHubRepo>>()
    val reposLiveData: LiveData<List<GitHubRepo>> get() = _reposLiveData

    fun loadTrendingRepos() {
        apiService.getTrendingRepos().enqueue(object : Callback<GitHubApiResponse> {
            override fun onResponse(call: Call<GitHubApiResponse>, response: Response<GitHubApiResponse>) {
                if (response.isSuccessful) {
                    val repos = response.body()?.items ?: emptyList()
                    _reposLiveData.value = repos
                } else {
                    // Handle error response
                }
            }

            override fun onFailure(call: Call<GitHubApiResponse>, t: Throwable) {
                // Handle network error
            }
        })
    }
    fun searchRepos(query: String) {
        val originalRepos = reposLiveData.value ?: emptyList()

        val filteredRepos = if (query.isNotBlank()) {
            originalRepos.filter { repo ->
                repo.name?.contains(query, ignoreCase = true) == true ||
                        repo.description?.contains(query, ignoreCase = true) == true
            }
        } else {

            originalRepos
        }

        _reposLiveData.postValue(filteredRepos)
        Log.d("GitHubViewModel", "Filtered Repos: $filteredRepos")
    }


}


