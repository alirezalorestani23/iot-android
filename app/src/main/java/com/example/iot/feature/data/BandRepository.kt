package com.example.iot.feature.data

import com.example.iot.core.data.Result

class BandRepository(private val bandService: BandService) {
    suspend fun getBands(): Result<BandsListResponse>? {
        return try {
            val response = bandService.getGuards()
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
