package com.example.weatherweather.core.data.local

import androidx.room.TypeConverter
import com.example.weatherweather.core.data.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromWeatherList(value: List<Weather>): String = gson.toJson(value)

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        val type = object : TypeToken<List<Weather>>() {}.type
        return gson.fromJson(value, type)
    }
}