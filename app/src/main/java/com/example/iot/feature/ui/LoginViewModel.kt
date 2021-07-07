package com.example.iot.feature.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iot.feature.data.LoginRepository
import kotlinx.coroutines.launch
import com.example.iot.core.data.Result

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {
    private val _loginView = MutableLiveData<Result<LoginResponse>>()
    val loginResponse: LiveData<Result<LoginResponse>>
        get() = _loginView


    fun login(username: String, password: String) {
        if (username.length > 3 && password.length > 5)
            viewModelScope.launch {
                _loginView.value = repository.login(username = username, password = password)
            }
    }


}