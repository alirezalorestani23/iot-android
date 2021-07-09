package com.example.iot.feature.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GuardService {

    @GET("api/active_guards/")
    suspend fun getActiveGuards(): Response<ActiveGuardsResponse>


    @GET("/api/guard_last_history/{staff_id}")
    suspend fun getGuardLastHistory(
        @Path("staff_id") staffId: String
    ): Response<GuardLastHistoryResponse>

    @GET("/api/guards_list")
    suspend fun getGuards(): Response<GuardsListResponse>
}