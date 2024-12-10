package com.ivan.myapplication.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivan.myapplication.ui.auth.LoginScreen
import com.ivan.myapplication.ui.auth.RegisterScreen
import com.ivan.myapplication.viewModel.auth.AuthViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun AuthNavigationApp(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by authViewModel.uiState.collectAsState()

    var startDestination by remember { mutableStateOf("loading") }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        launchSingleTop = true
                        popUpTo(Screen.MainPage.route) {
                            saveState = true
                        }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                },
                authViewModel = authViewModel
            )
        }

        composable(route = "register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate("main") {
                        launchSingleTop = true
                        popUpTo(Screen.MainPage.route) {
                            saveState = true
                        }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        composable(route = "main") {
            NavigationApp(authViewModel)
        }

        composable(route = "loading") {
            Text("Loading...")

            try {
                authViewModel.checkAuthentication()
                startDestination = if (uiState.isAuthenticated) "main" else "login"
            } catch (_: Exception) { startDestination = "login" }
        }
    }
}