package com.example.weatherweather.core.network

import com.example.weatherweather.core.data.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getCurrentWeatherByCity(
        @Query("q") cityCountry: String,
        @Query("units") units: String = "metric"
    ): CurrentWeatherResponse

}

