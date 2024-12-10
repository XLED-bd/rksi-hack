package com.ivan.myapplication.ui.mainpage

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MainScreen(onLogout: () -> Unit) {
    Column {
        Text("Welcome to the main screen!")
    }
}