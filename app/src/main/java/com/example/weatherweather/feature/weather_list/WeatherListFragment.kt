package com.example.weatherweather.feature.weather_list

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.weatherweather.R
import com.example.weatherweather.core.base.BaseFragment
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.extension.observe
import com.example.weatherweather.core.utils.setVisibleOrGone
import com.example.weatherweather.databinding.FragmentWeatherListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherListFragment : BaseFragment<FragmentWeatherListBinding>() {

    @Inject
    lateinit var adapter: WeatherListAdapter

    override val layoutRes: Int
        get() = R.layout.fragment_weather_list

    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: WeatherListViewModel by viewModels()

    override fun onCreated(savedInstance: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.apply {
            observe(allWeather) { weatherList ->
                weatherList?.let {
                    binding.tvNoData.setVisibleOrGone(false)
                    adapter.collection = it
                }
            }
        }
    }

    private fun initViews() {
        with(binding) {
            rvWeatherList.adapter = adapter
            tvNoData.setVisibleOrGone(true)
        }

    }
}