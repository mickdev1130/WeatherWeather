package com.example.weatherweather.feature.current_weather

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.weatherweather.R
import com.example.weatherweather.core.base.BaseFragment
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.base.State
import com.example.weatherweather.core.data.CurrentWeatherResponse
import com.example.weatherweather.core.extension.getWeatherIcon
import com.example.weatherweather.core.extension.observe
import com.example.weatherweather.core.extension.toFormattedTimeLabel
import com.example.weatherweather.core.extension.toLocationString
import com.example.weatherweather.core.extension.toTemperatureString
import com.example.weatherweather.databinding.FragmentCurrentWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentWeatherFragment : BaseFragment<FragmentCurrentWeatherBinding>() {


    override val layoutRes: Int
        get() = R.layout.fragment_current_weather

    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: CurrentWeatherViewModel by viewModels()

    override fun onCreated(savedInstance: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initObservers() {
        with(viewModel) {
            observe(weatherState) { weather ->
                when (weather) {
                    is State.Success<*> -> {
                        val weatherData = weather.data as? CurrentWeatherResponse
                        weatherData?.let {
                            bindWeatherData(it)
                        }
                    }
                    else -> {}
                }
            }
        }
    }

    private fun initViews() {}

    private fun bindWeatherData(weather: CurrentWeatherResponse) {
        with(binding) {
            tvLocation.text = toLocationString(weather.name, weather.sys.country)
            tvTemperature.text = weather.main.temp.toTemperatureString()
            tvSunrise.text = weather.sys.sunrise.toFormattedTimeLabel("Sunrise")
            tvSunset.text = weather.sys.sunset.toFormattedTimeLabel("Sunset")
            ivIcon.setImageResource(getWeatherIcon())
        }
    }

}