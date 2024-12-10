package com.ivan.myapplication.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    var startDestination = "login"

    try {
        authViewModel.checkAuthentication()
        startDestination = if (uiState.isAuthenticated) "main" else "login"
    } catch (_: Exception) { }

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
    }
}