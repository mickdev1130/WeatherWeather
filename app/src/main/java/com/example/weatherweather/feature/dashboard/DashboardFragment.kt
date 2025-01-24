package com.example.weatherweather.feature.dashboard

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.weatherweather.R
import com.example.weatherweather.core.base.BaseFragment
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.databinding.FragmentDashboardBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {

    override val layoutRes: Int
        get() = R.layout.fragment_dashboard


    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreated(savedInstance: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initObservers() {}

    private fun initViews() {
        with(binding) {
            val adapter = DashboardPagerAdapter(childFragmentManager, lifecycle)
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Current Weather"
                    1 -> tab.text = "Forecast List"
                }
            }.attach()
        }
    }


}