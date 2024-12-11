package com.ivan.myapplication.ui.auth

import android.view.WindowInsets
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivan.myapplication.viewModel.auth.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    authViewModel: AuthViewModel
) {
    val uiState by authViewModel.uiState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
    {
    Column(
        Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally
    )
    {
        Text("Вход",fontSize = 36.sp,textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp).padding(top = 40.dp),
        )

        Spacer(modifier = Modifier.height(150.dp))

        OutlinedTextField(
            value = email,

            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color(0xff888888),
                focusedContainerColor = Color.White,
                focusedTextColor = Color(0xff888888),
                focusedBorderColor= Color(0xFFff0000),
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
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
                .background(Color.White),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color(0xff888888),
                focusedContainerColor = Color.White,
                focusedTextColor = Color(0xff888888),
                focusedBorderColor= Color(0xFFff0000),
                unfocusedBorderColor = Color(0xFFff0000),
                unfocusedLabelColor = Color(0xff888888),
                focusedLabelColor = Color(0xff888888),
            )
        )

        Spacer(modifier = Modifier.height(23.dp))

        Button(
            onClick = { authViewModel.login(email, password) },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFffffff),
                containerColor = Color(0xFFff0000)
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(23.dp))

        Text("No account?",fontSize = 20.sp,textAlign = TextAlign.Center, modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        )

        Spacer(modifier = Modifier.height(23.dp))

        OutlinedButton(
            onClick = onNavigateToRegister,

            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFff0000), containerColor = Color(0xFFffffff)
            ),
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier

                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text("Register")
        }
    }
        when {
            uiState.isLoading -> Text("Logging in...")
            uiState.isAuthenticated -> onLoginSuccess()
            uiState.error != null -> Text("Error: ${uiState.error}")
        }
    }
}