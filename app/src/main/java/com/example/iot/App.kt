package com.example.iot

import android.app.Application
import android.content.Context
import com.example.iot.core.data.SharedPreferencesManager
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
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

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("http://185.211.58.230/")
            .client(okHttpClient)
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