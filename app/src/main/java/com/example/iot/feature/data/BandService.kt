package com.example.iot.feature.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BandService {

    @GET("/api/bands_list/")
    suspend fun getGuards(): Response<BandsListResponse>

    @POST("/api/edit_wristband_guard/")
    suspend fun assignBand(
        @Body editBandGuardBody: EditBandGuardBody
    ): Response<EditBandResponse>

}
