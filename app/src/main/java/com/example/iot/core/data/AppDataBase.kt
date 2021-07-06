package com.example.iot

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iot.feature.data.GuardDao
import com.example.iot.feature.data.GuardEntity

@Database(
    entities = [
        GuardEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGuardDao(): GuardDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "IOT_PROJECT"
            ).build()
        }
    }
}