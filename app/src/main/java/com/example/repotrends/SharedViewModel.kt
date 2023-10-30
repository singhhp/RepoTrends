package com.example.repotrends

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedItems: MutableSet<Int> = mutableSetOf()
    var currentSearchQuery: String = ""
}