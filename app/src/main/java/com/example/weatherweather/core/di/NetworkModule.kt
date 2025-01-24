package com.example.weatherweather.core.di

import com.example.weatherweather.core.network.ApiService
import com.example.weatherweather.core.utils.Keys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val urlWithApiKey = originalRequest.url().newBuilder()
                    .addQueryParameter("appid", Keys.OpenWeatherApiKey())
                    .build()

                val newRequest = originalRequest.newBuilder()
                    .url(urlWithApiKey)
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideOpenWeatherService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}