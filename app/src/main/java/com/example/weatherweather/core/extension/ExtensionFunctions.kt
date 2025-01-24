package com.example.weatherweather.core.extension

import android.util.Patterns
import com.example.weatherweather.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun getWeatherIcon(): Int {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return if (currentHour >= 18) {
        R.drawable.ic_moon
    } else {
        R.drawable.ic_sun
    }
}

fun toLocationString(cityName: String, countryCode: String): String {
    return "$cityName, $countryCode"
}

fun Double.toTemperatureString(): String {
    return "${this}°C"
}

fun Long.toFormattedTimeLabel(label: String): String {
    return "$label: ${this.toFormattedTime()}"
}

fun Long.toFormattedTime(): String {
    val date = Date(this * 1000)
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(date)
}