package com.ivan.myapplication.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivan.myapplication.viewModel.auth.AuthViewModel

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    authViewModel: AuthViewModel
) {
    val uiState by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = { authViewModel.register(email, password) }) {
            Text("Register")
        }

        TextButton(onClick = onNavigateToLogin) {
            Text("Already have an account? Login")
        }

        when {
            uiState.isLoading -> Text("Registering...")
            uiState.isAuthenticated -> onRegisterSuccess()
            uiState.error != null -> Text("Error: ${uiState.error}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val authViewModel: AuthViewModel = hiltViewModel()
    RegisterScreen(
        onRegisterSuccess = { },
        onNavigateToLogin = { },
        authViewModel = authViewModel
    )
}