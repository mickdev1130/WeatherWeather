package com.example.weatherweather.feature.authentication

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherweather.R
import com.example.weatherweather.core.base.BaseFragment
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.base.State
import com.example.weatherweather.core.extension.observe
import com.example.weatherweather.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {


    override val layoutRes: Int
        get() = R.layout.fragment_login

    override fun getViewModel(): BaseViewModel = viewModel

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreated(savedInstance: Bundle?) {
        initViews()
        initObservers()
    }

    private fun initObservers() {
        viewModel.apply {
            observe(loginState) { state ->
                when (state) {
                    is State.Success<*> -> {
                        setLoadingState(State.Idle)
                        findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)

                    }
                    is State.Error -> {
                        setLoadingState(State.Idle)
                        showMessageDialog(state.message)
                    }
                    else -> {}
                }

            }
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun initViews() {
        binding.apply {
            bLogin.setOnClickListener {
                val email = "test@gmail.com"//tieEmail.text.toString()
                val password = "hello123"//tiePassword.text.toString()
                viewModel?.login(email, password)
            }
        }

    }

}