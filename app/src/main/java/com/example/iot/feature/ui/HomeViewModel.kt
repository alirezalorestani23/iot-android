package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.feature.data.Guard
import com.example.iot.feature.data.GuardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(val repository: GuardRepository) : ViewModel() {

    private val _guards = MutableLiveData<List<Guard>>()
    val guards: LiveData<List<Guard>>
        get() = _guards

    fun fetchGuards() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getGuardPosition()
            _guards.postValue(data)
        }
    }
}