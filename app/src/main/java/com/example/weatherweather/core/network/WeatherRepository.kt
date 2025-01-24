package com.example.weatherweather.core.network

import androidx.lifecycle.LiveData
import com.example.weatherweather.core.data.CurrentWeatherResponse
import com.example.weatherweather.core.data.local.WeatherDao
import com.example.weatherweather.core.data.local.WeatherEntity
import com.example.weatherweather.core.data.local.toEntity
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) {

    suspend fun getCurrentWeatherByCity(city: String, country: String): CurrentWeatherResponse {
        val response = apiService.getCurrentWeatherByCity("$city,$country")
        saveWeather(response)
        return response
    }

    private suspend fun saveWeather(weather: CurrentWeatherResponse) {
        val weatherEntity = weather.toEntity()
        weatherDao.insertWeather(weatherEntity)
    }

    fun getAllWeather(): LiveData<List<WeatherEntity>> {
        return weatherDao.getAllWeather()
    }

}