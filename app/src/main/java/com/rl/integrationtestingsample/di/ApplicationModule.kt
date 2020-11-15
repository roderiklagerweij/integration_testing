package com.rl.integrationtestingsample.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.rl.integrationtestingsample.data.LoginApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object ApplicationModule {

    @JvmStatic
    @Provides
    fun sharedPref(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)

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