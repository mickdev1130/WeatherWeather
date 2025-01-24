package com.example.weatherweather.core.utils

object Keys {

    init {
        System.loadLibrary("native-lib")
    }
    external fun OpenWeatherApiKey() :String
}