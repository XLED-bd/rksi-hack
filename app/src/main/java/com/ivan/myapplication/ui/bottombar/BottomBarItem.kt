package com.ivan.myapplication.ui.bottombar


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.ivan.myapplication.navigation.Screen

sealed class BottomBarItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarItem(
        route = Screen.MainPage.route,
        title = "Главная",
        icon = Icons.Default.Home
    )
    object Tikets : BottomBarItem(
        route = Screen.TiketsPage.route,
        title = "Билеты",
        icon = Icons.Default.ShoppingCart
    )

    //object Setting : BottomBarItem(
    //    route = Screen.SettingPage.route,
    //    title = "Настройки",
    //    icon = Icons.Default.Settings
    //)
}