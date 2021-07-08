package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.core.data.Result
import com.example.iot.feature.data.ActiveGuardsResponse
import com.example.iot.feature.data.GuardLastHistoryResponse
import com.example.iot.feature.data.GuardRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GuardRepository) : ViewModel() {

    private val _activeGuards = MutableLiveData<Result<ActiveGuardsResponse>>()
    val activeGuards: LiveData<Result<ActiveGuardsResponse>>
        get() = _activeGuards


    private val _guardLastHistory = MutableLiveData<Result<GuardLastHistoryResponse>>()
    val guardLastHistory: LiveData<Result<GuardLastHistoryResponse>>
        get() = _guardLastHistory


    fun fetchActiveGuards() {
        viewModelScope.launch() {
//            _activeGuards.value = repository.getGuardPosition()
            _activeGuards.value = repository.getActiveGuards()
        }
    }

    fun getGuardLastHistory(staffId: Int) {
        viewModelScope.launch {
            _guardLastHistory.value = repository.getGuardLastHistory(staffId)
        }

    }
}