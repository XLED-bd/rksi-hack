package com.ivan.myapplication.viewModel.auth

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivan.myapplication.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
data class AuthUIState(
    val isLoading: Boolean = false,
    val isAuthenticated: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUIState())
    val uiState: StateFlow<AuthUIState> = _uiState

    companion object {
        private const val TOKEN_KEY = "jwt_token"
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUIState(isLoading = true)

            try {
                val response = apiService.login(
                    mapOf("email" to email, "password" to password)
                )
                saveToken(response.token)
                _uiState.value = AuthUIState(isLoading = false, isAuthenticated = true)
            } catch (e: Exception) {
                _uiState.value = AuthUIState(isLoading = false, error = e.localizedMessage)
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUIState(isLoading = true)

            try {
                val response = apiService.register(
                    mapOf("email" to email, "password" to password)
                )
                saveToken(response.token)
                _uiState.value = AuthUIState(isLoading = false, isAuthenticated = true)
            } catch (e: Exception) {
                _uiState.value = AuthUIState(isLoading = false, error = e.localizedMessage)
            }
        }
    }

    fun checkAuthentication() {
        val token = getToken()
        _uiState.value = AuthUIState(isAuthenticated = token != null)
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun logout() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
        _uiState.value = AuthUIState(isAuthenticated = false)
    }
}