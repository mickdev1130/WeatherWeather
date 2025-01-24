package com.example.weatherweather.feature.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherweather.core.base.BaseViewModel
import com.example.weatherweather.core.base.State
import com.example.weatherweather.core.extension.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : BaseViewModel() {

    private val _loginState = MutableLiveData<State>()
    val loginState: LiveData<State> get() = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {

            if (email.isEmpty() || !email.isValidEmail()) {
                _loginState.value = State.Error("Invalid email address")
                return@launch
            }

            if (password.isEmpty()) {
                _loginState.value = State.Error("Password cannot be empty")
                return@launch
            }

            setLoadingState(State.Loading)
            delay(2.seconds)
            viewModelScope.launch {
                try {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { status ->
                            if (status.isSuccessful) {
                                _loginState.value = State.Success("Login Successful")
                            } else {
                                _loginState.value = State.Error("Login Failed: ${status.exception?.message}")
                            }
                        }
                    setLoadingState(State.Idle)
                } catch (e: Exception) {
                    setLoadingState(State.Idle)
                    _loginState.value = State.Error("Error: ${e.message}")
                }
            }
        }
    }

}


