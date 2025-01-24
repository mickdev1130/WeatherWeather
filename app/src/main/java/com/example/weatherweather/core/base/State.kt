package com.example.weatherweather.core.base

sealed class State {
    object Idle : State()
    object Loading : State()
    data class Error(val message: String) : State()
    data class Success<T>(val data: T? = null) : State()
}