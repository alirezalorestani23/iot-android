package com.example.iot.feature.data

import com.example.iot.core.data.Result
import com.example.iot.feature.ui.LoginResponse


class LoginRepository(private val remote: LoginService) {

    suspend fun login(username: String, password: String): Result<LoginResponse>? {
        return try {
            val response = remote.login(username, password)

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                }
            } else Result.Error(response.message())
        } catch (ex: Exception) {
            Result.Error(ex.message ?: "EX")
        }

    }

}