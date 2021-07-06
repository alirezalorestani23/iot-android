package com.example.iot.feature.data

class GuardRepository(private val guardService: GuardService, private val guardDao: GuardDao) {

    suspend fun getGuardPosition(): List<Guard> {
        return listOf(
            Guard(1, "Rahim", "Jalali", 36.315846, 59.530222),
            Guard(2, "Rahim", "Jalali", 36.313339, 59.529253),
            Guard(3, "Rahim", "Jalali", 36.310954, 59.533420),
            Guard(4, "Rahim", "Jalali", 36.307406, 59.520140),
            Guard(5, "Rahim", "Jalali", 36.303639, 59.530528)
        )
    }
}
