package com.ivan.myapplication.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ivan.myapplication.ui.bottombar.BottomNavigationBar
import com.ivan.myapplication.ui.listinvestments.ListInvestments
import com.ivan.myapplication.ui.mainpage.MainScreen
import com.ivan.myapplication.ui.profile.Portfolio.Portfolio
import com.ivan.myapplication.ui.profile.ProfilePage
import com.ivan.myapplication.ui.profile.history.History
import com.ivan.myapplication.ui.stock.StockPage
import com.ivan.myapplication.ui.topbar.Topbar
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
                topBar = { Topbar("Главная") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                MainScreen(Modifier.padding(innerPadding), viewModel,
                    onLogout = { authViewModel.logout() },
                    onInvestClick = { navController.navigate(Screen.ListInvestmentsPage.route) })
            }
        }

        composable(Screen.ListInvestmentsPage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = { Topbar("Все акции") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                ListInvestments(Modifier.padding(innerPadding), viewModel, onClickStock = {
                    navController.navigate("stock/$it")
                })
            }
        }

        composable("stock/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType})){ id ->
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = { Topbar("Акция") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                StockPage(Modifier.padding(innerPadding),
                    viewModel,
                    id.arguments!!.getString("id"))
            }
        }

        composable(Screen.ProfilePage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = { Topbar("Профиль") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                ProfilePage(Modifier.padding(innerPadding), onClickHistory = {
                    navController.navigate(Screen.HistoryPage.route)
                },
                    onClickPortfolio = {
                        navController.navigate(Screen.PortfolioPage.route)
                    })
            }
        }

        composable(Screen.HistoryPage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = { Topbar("История") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                History(Modifier.padding(innerPadding), viewModel)
            }
        }

        composable(Screen.PortfolioPage.route){
            Scaffold(modifier = Modifier.fillMaxSize(),
                topBar = { Topbar("Портфель") },
                bottomBar = {  BottomNavigationBar(navController, viewModel) }
            ) { innerPadding ->
                Portfolio(Modifier.padding(innerPadding), viewModel)
            }
        }
    }
}

