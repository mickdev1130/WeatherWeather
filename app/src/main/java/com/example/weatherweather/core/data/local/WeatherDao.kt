package com.example.weatherweather.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherList(weatherList: List<WeatherEntity>)

    @Query("SELECT * FROM weather_table ORDER BY id DESC")
    fun getAllWeather(): LiveData<List<WeatherEntity>>
}
