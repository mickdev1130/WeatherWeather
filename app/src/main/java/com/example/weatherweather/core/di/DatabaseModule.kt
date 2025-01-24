package com.example.weatherweather.core.di

import android.content.Context
import androidx.room.Room
import com.example.weatherweather.core.data.local.WeatherDao
import com.example.weatherweather.core.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): WeatherDatabase {
        return Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database").build()
    }

    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao {
        return database.weatherDao()
    }
}
