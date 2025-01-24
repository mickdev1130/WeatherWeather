package com.example.weatherweather.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun setError(value: String) {
        _error.value = value
    }

    fun setLoadingState(state: State) {
        when (state) {
            is State.Idle -> {
                _loading.value = false
            }
            else -> {
                _loading.value = true
            }
        }
    }
}