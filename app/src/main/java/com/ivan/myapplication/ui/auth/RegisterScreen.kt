package com.ivan.myapplication.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    //var email by remember { mutableStateOf("") }
    var again_password by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier.fillMaxSize(), Arrangement.Center
        )
        {
            Text(
                "Регистрация", fontSize = 36.sp, textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(150.dp))
            OutlinedTextField(
                value = email,

                onValueChange = { email = it },
                label = { Text("Почта") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color(0xff888888),
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color(0xff888888),
                    focusedBorderColor = Color(0xFFff0000),
                    unfocusedBorderColor = Color(0xFFff0000),
                    unfocusedLabelColor = Color(0xff888888),
                    focusedLabelColor = Color(0xff888888),
                )
            )

            Spacer(modifier = Modifier.height(23.dp))

            OutlinedTextField(
                value = "",

                onValueChange = { },
                label = { Text(" ") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color(0xff888888),
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color(0xff888888),
                    focusedBorderColor = Color(0xFFff0000),
                    unfocusedBorderColor = Color(0xFFff0000),
                    unfocusedLabelColor = Color(0xff888888),
                    focusedLabelColor = Color(0xff888888),
                )
            )

            Spacer(modifier = Modifier.height(23.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .background(Color.White),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color(0xff888888),
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color(0xff888888),
                    focusedBorderColor = Color(0xFFff0000),
                    unfocusedBorderColor = Color(0xFFff0000),
                    unfocusedLabelColor = Color(0xff888888),
                    focusedLabelColor = Color(0xff888888),
                )
            )

            Spacer(modifier = Modifier.height(23.dp))
            OutlinedTextField(
                value = "",

                onValueChange = { },
                label = { Text("Password again") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    unfocusedTextColor = Color(0xff888888),
                    focusedContainerColor = Color.White,
                    focusedTextColor = Color(0xff888888),
                    focusedBorderColor = Color(0xFFff0000),
                    unfocusedBorderColor = Color(0xFFff0000),
                    unfocusedLabelColor = Color(0xff888888),
                    focusedLabelColor = Color(0xff888888),
                )
            )

            Spacer(modifier = Modifier.height(26.dp))

            Button(
                onClick = { authViewModel.register(email, password) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFFffffff),
                    containerColor = Color(0xFFff0000)
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text("Register")

            }

            TextButton(
                onClick = onNavigateToLogin,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xFFff0000), containerColor = Color(0xFFffffff)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text("Already have an account? Login")
            }
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