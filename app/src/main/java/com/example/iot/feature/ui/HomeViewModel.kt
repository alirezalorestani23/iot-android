package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.core.data.Result
import com.example.iot.feature.data.Guard
import com.example.iot.feature.data.GuardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: GuardRepository) : ViewModel() {

    private val _guards = MutableLiveData<Result<List<Guard>>>()
    val guards: LiveData<Result<List<Guard>>>
        get() = _guards

    fun fetchGuards() {
        viewModelScope.launch() {
            _guards.value = repository.getGuardPosition()
        }
    }
}