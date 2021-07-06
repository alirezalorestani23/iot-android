package com.example.iot

import android.app.Application
import android.content.Context
import com.example.iot.core.data.SharedPreferencesManager
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    companion object {
        lateinit var retrofit: Retrofit
        lateinit var roomDatabase: AppDatabase
        lateinit var preferences: SharedPreferencesManager
        lateinit var context: Context
        lateinit var gSon: Gson
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        roomDatabase = provideDB(context)
        retrofit = provideRetrofit()
        preferences = provideSharedPreferences(context)
        gSon = provideGSon()
        retrofit = provideRetrofit()
    }


    private fun provideDB(context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    private fun provideSharedPreferences(context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    private fun provideGSon(): Gson {
        return Gson()
    }
}