package com.rl.integrationtestingsample.di

import android.app.Application
import android.content.SharedPreferences
import com.rl.integrationtestingsample.data.LoginApi
import com.rl.integrationtestingsample.util.InMemorySharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object TestApplicationModule {

    @JvmStatic
    @Singleton
    @Provides
    fun sharedPref(): SharedPreferences =
        InMemorySharedPreferences()

    @JvmStatic
    @Provides
    @Singleton
    fun loginApi(
    ): LoginApi = Retrofit.Builder()
        .baseUrl("http://localhost:49812/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()))
        .build().create(LoginApi::class.java)

}