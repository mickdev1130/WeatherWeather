package com.example.weatherweather.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.weatherweather.core.data.local.Converters

@Entity(tableName = "weather_table")
@TypeConverters(Converters::class)
data class CurrentWeatherResponse(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val weather: List<Weather>,
    val main: Main,
    val sys: Sys,
    val name: String
)

data class Weather(
    val description: String
)

data class Main(
    val temp: Double
)

data class Sys(
    val sunrise: Long,
    val sunset: Long,
    val country: String
)



