package com.example.weatherweather.core.utils

import android.view.View

fun View.setVisibleOrGone(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}
