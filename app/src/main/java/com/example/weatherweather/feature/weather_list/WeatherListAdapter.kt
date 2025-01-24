package com.example.weatherweather.feature.weather_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherweather.R
import com.example.weatherweather.core.data.local.WeatherEntity
import com.example.weatherweather.core.extension.toFormattedTimeLabel
import com.example.weatherweather.core.extension.toLocationString
import com.example.weatherweather.core.extension.toTemperatureString
import com.example.weatherweather.core.utils.AutoUpdatableAdapter
import com.example.weatherweather.databinding.ItemWeatherBinding
import javax.inject.Inject
import kotlin.properties.Delegates

class WeatherListAdapter @Inject constructor() :
    RecyclerView.Adapter<WeatherListAdapter.Holder>(),
    AutoUpdatableAdapter {

    internal var collection: List<WeatherEntity> by Delegates.observable(emptyList()) { prop, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder.from(
            parent,
            R.layout.item_weather
        )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binding.apply {
            val weather = collection[position]
            tvLocation.text = toLocationString(weather.name, weather.country)
            tvTemperature.text = weather.temperature.toTemperatureString()
            tvSunrise.text = weather.sunrise.toFormattedTimeLabel("Sunrise")
            tvSunset.text = weather.sunset.toFormattedTimeLabel("Sunset")
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = collection.size

    class Holder(val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup, layout: Int): Holder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil.inflate<ItemWeatherBinding>(
                    inflater,
                    layout,
                    parent,
                    false
                )
                return Holder(
                    binding
                )
            }
        }
    }
}
