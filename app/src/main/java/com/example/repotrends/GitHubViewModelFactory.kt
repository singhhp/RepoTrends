package com.example.repotrends.viewmodels

import GitHubApiService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider




class GitHubViewModelFactory(private val apiService: GitHubApiService) : ViewModelProvider.Factory {

    @JvmSuppressWildcards
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GitHubViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}