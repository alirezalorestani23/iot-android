package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.core.data.Result
import com.example.iot.feature.data.BandRepository
import com.example.iot.feature.data.BandsListResponse
import kotlinx.coroutines.launch

class BandsListViewModel(private val repository: BandRepository) : ViewModel() {
    private val _bandsList = MutableLiveData<Result<BandsListResponse>>()
    val bandsList: LiveData<Result<BandsListResponse>>
        get() = _bandsList


    fun getBands() {
        viewModelScope.launch {
            _bandsList.value = repository.getBands()
        }
    }

}
