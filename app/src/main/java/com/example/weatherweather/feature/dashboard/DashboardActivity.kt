package com.example.weatherweather.feature.dashboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherweather.R
import com.example.weatherweather.core.base.BaseActivity
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override val layoutRes: Int
        get() = R.layout.activity_dashboard

    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreated(instance: Bundle?) {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clParent)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}