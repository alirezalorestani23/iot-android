package com.example.iot.feature.data

import com.example.iot.feature.ui.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @POST("api/login/")
    suspend fun login(
        @Body loginBody: LoginBody
    ): Response<LoginResponse>

}
