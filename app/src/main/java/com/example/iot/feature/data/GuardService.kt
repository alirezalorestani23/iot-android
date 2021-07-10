package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.*

interface GuardService {

    @GET("api/active_guards/")
    suspend fun getActiveGuards(): Response<ActiveGuardsResponse>


    @GET("/api/guard_last_history/{staff_id}")
    suspend fun getGuardLastHistory(
        @Path("staff_id") staffId: String
    ): Response<GuardLastHistoryResponse>

    @GET("/api/guards_list")
    suspend fun getGuards(): Response<GuardsListResponse>

    @GET("/api/guard_profile/{staff_id}")
    suspend fun getGuardProfile(
        @Path("staff_id") staffId: String
    ): Response<GuardProfileResponse>

    @HTTP(method = "DELETE", path = "/api/delete_guard/", hasBody = true)
    suspend fun deleteGuard(    
        @Body staffBody: StaffBody
    ): Response<DeleteGuardResponse>
}