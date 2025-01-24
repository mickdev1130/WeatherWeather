package com.example.weatherweather.feature.current_weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.base.State
import com.example.weatherweather.core.data.CurrentWeatherResponse
import com.example.weatherweather.core.network.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    init {
        getCurrentWeather()
    }

    private val _weatherState = MutableLiveData<State>()
    val weatherState: LiveData<State> get() = _weatherState

    fun getCurrentWeather() {
        viewModelScope.launch {
            setLoadingState(State.Loading)
            try {

                val weather = weatherRepository.getCurrentWeatherByCity("Manila", "PH")
                println("testing $weather")

                if (weather != null) {
                    _weatherState.value = State.Success(weather as? CurrentWeatherResponse)
                } else {
                    _weatherState.value = State.Error("Weather data is null")
                }
            } catch (e: Exception) {
                _weatherState.value = State.Error("Failed to fetch weather: ${e.message}")
            }
        }
        setLoadingState(State.Idle)
    }
}