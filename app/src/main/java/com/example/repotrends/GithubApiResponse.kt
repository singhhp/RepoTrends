package com.example.repotrends.model

import com.example.repotrends.model.GitHubRepo

data class GitHubApiResponse(
    val items: List<GitHubRepo>
)
