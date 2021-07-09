package com.example.iot.feature.data

import com.example.iot.core.data.Result
import kotlinx.coroutines.delay

class GuardRepository(private val guardService: GuardService, private val guardDao: GuardDao) {

//    suspend fun getGuardPosition(): Result<List<ActiveGuard>> {
//        delay(1000)
//        return Result.Success(
//            listOf(
//                ActiveGuard(1, "Rahim", "Jalali", 36.315846, 59.530222),
//                ActiveGuard(2, "Rahim", "Jalali", 36.313339, 59.529253),
//                ActiveGuard(3, "Rahim", "Jalali", 36.310954, 59.533420),
//                ActiveGuard(4, "Rahim", "Jalali", 36.307406, 59.520140),
//                ActiveGuard(5, "Rahim", "Jalali", 36.303639, 59.530528)
//            )
//        )
//    }

    suspend fun getActiveGuards(): Result<ActiveGuardsResponse>? {
        return try {
            val response = guardService.getActiveGuards()

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                }
            } else Result.Error(response.message())
        } catch (ex: Exception) {
            Result.Error(ex.message ?: "EX")
        }
    }

    suspend fun getGuardLastHistory(staffId: Int): Result<GuardLastHistoryResponse>? {
        return try {
            val response = guardService.getGuardLastHistory(staffId.toString())

            if (response.isSuccessful) {
                response.body()?.let {
                    Result.Success(it)
                }
            } else Result.Error(response.message())
        } catch (ex: Exception) {
            Result.Error(ex.message ?: "EX")
        }
    }

    suspend fun getGuards(): Result<GuardsListResponse>? {
        return try {
            val response = guardService.getGuards()
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
