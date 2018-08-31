package com.example.luthfihariz.footballclub.di

import android.util.Log
import com.example.luthfihariz.footballclub.BuildConfig
import com.example.luthfihariz.footballclub.data.remote.ApiService
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = applicationContext {
    bean("logging") {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d("ApiLog", message)
        }).setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor
    }

    bean {
        OkHttpClient.Builder()
                .addInterceptor(get("logging"))
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
    }

    bean {
        val gson = GsonBuilder().setLenient().create()


        Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY)
                .client(get() as OkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
    }
}