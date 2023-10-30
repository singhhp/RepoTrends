package com.example.repotrends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectedItemViewModel : ViewModel() {
    private val _selectedItemPosition = MutableLiveData<Int>()
    val selectedItemPosition: LiveData<Int> get() = _selectedItemPosition

    fun setSelectedItem(position: Int) {
        _selectedItemPosition.value = position
    }
}
