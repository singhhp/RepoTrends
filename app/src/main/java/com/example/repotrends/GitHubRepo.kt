package com.example.repotrends.model

import android.widget.ImageView


data class GitHubRepo(val name: String, val owner: GitHubOwner,val description: String)

data class GitHubOwner(val login: String)

