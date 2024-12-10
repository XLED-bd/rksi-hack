package com.ivan.bus.ui.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/*
@Composable
fun BottomNavigationBar(navController: NavController, viewModel: MainViewModel) {

    val items = listOf(
        BottomBarItem.Home,
        BottomBarItem.Tikets,
        BottomBarItem.Setting,
        )


    val selectedItem by viewModel.selectedItem.collectAsState()

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = selectedItem == item,
                onClick = {
                    viewModel.onItemSelected(item)
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}*/