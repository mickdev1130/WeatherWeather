package com.example.weatherweather.feature.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherweather.feature.current_weather.CurrentWeatherFragment
import com.example.weatherweather.feature.weather_list.WeatherListFragment

class DashboardPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CurrentWeatherFragment()
            1 -> WeatherListFragment()
            else -> throw IllegalStateException("Invalid tab position")
        }
    }
}