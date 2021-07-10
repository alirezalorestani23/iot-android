package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.feature.data.GuardProfileResponse
import com.example.iot.feature.data.GuardRepository
import kotlinx.coroutines.launch
import com.example.iot.core.data.Result
import com.example.iot.feature.data.DeleteGuardResponse
import com.example.iot.feature.data.StaffBody

class GuardProfileViewModel(private val repository: GuardRepository) : ViewModel() {

    private val _guardProfile = MutableLiveData<Result<GuardProfileResponse>>()
    val guardsProfile: LiveData<Result<GuardProfileResponse>>
        get() = _guardProfile


    private val _deleteGuard = MutableLiveData<Result<DeleteGuardResponse>>()
    val deleteGuard: LiveData<Result<DeleteGuardResponse>>
        get() = _deleteGuard


    fun getGuardProfile(staffId: Int) {
        viewModelScope.launch {
            _guardProfile.value = repository.getGuardProfile(staffId)
        }
    }

    fun deleteGuard(staffId: Int) {
        viewModelScope.launch {
            _deleteGuard.value = repository.deleteGuard(StaffBody(staffId.toString()))
        }
    }
}
