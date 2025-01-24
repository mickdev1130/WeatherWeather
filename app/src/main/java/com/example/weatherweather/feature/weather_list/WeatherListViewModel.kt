package com.example.weatherweather.feature.weather_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.data.local.WeatherEntity
import com.example.weatherweather.core.network.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    val allWeather: LiveData<List<WeatherEntity>> = weatherRepository.getAllWeather()

}