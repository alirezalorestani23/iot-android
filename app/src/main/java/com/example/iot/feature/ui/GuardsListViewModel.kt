package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.core.data.Result
import com.example.iot.feature.data.GuardRepository
import com.example.iot.feature.data.GuardsListResponse
import kotlinx.coroutines.launch

class GuardsListViewModel(private val repository: GuardRepository) : ViewModel() {

    private val _guardsList = MutableLiveData<Result<GuardsListResponse>>()
    val guardsList: LiveData<Result<GuardsListResponse>>
        get() = _guardsList


    fun getGuards() {
        viewModelScope.launch {
            _guardsList.value = repository.getGuards()
        }
    }
}
