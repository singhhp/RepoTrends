package com.example.repotrends

import com.example.repotrends.model.GitHubRepo

data class GitHubViewState(
    val loading: Boolean = false,
    val repos: List<GitHubRepo> = emptyList(),
    val error: String? = null
)
