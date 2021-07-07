package com.example.iot.feature.data

import com.example.iot.feature.ui.LoginResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("api/login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String

    ): Response<LoginResponse>

}
