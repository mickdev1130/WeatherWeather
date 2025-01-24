package com.example.weatherweather.core.data.local


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherweather.core.data.CurrentWeatherResponse

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val country: String,
    val temperature: Double,
    val sunrise: Long,
    val sunset: Long
)

fun CurrentWeatherResponse.toEntity(): WeatherEntity {
    return WeatherEntity(
        name = name,
        country = sys.country,
        temperature = main.temp,
        sunrise = sys.sunrise,
        sunset = sys.sunset
    )
}
