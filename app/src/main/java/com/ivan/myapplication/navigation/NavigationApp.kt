package com.ivan.myapplication.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ivan.myapplication.ui.bottombar.BottomNavigationBar
import com.ivan.myapplication.ui.listinvestments.ListInvestments
import com.ivan.myapplication.ui.mainpage.MainScreen
import com.ivan.myapplication.viewModel.MainViewModel
import com.ivan.myapplication.viewModel.auth.AuthViewModel

@Composable
fun NavigationApp(authViewModel: AuthViewModel) {

    val navController = rememberNavController()

    val viewModel: MainViewModel = hiltViewModel()

    NavHost(
        navController= navController,
        startDestination = Screen.MainPage.route
    ) {
        composable(Screen.MainPage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {  },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                MainScreen(Modifier.padding(innerPadding),
                    onLogout = { authViewModel.logout() })
            }
        }

        composable(Screen.ListInvestmentsPage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = {  },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                ListInvestments(Modifier.padding(innerPadding))
            }
        }
    }



}

