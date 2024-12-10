package com.ivan.myapplication.ui.bottombar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.ivan.myapplication.navigation.Screen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        route = Screen.MainPage.route,
        title = "Главная",
        icon = Icons.Default.Home
    )
    object ListInvestments : BottomNavItem(
        route = Screen.ListInvestmentsPage.route,
        title = "Все активы",
        icon = Icons.Default.ShoppingCart
    )

    object Profile : BottomNavItem(
        route = Screen.ProfilePage.route,
        title = "Профиль",
        icon = Icons.Default.AccountCircle
    )
}