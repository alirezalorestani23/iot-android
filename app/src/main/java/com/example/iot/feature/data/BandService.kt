package com.example.iot.feature.data

import retrofit2.Response
import retrofit2.http.GET

interface BandService {

    @GET("/api/bands_list/")
    suspend fun getGuards(): Response<BandsListResponse>

}
