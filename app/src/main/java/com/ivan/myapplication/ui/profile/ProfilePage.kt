package com.ivan.myapplication.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfilePage(modifier: Modifier, onClickHistory: () -> Unit, onClickPortfolio: () -> Unit) {
    Column(modifier){
        Text("Профиль")
        Button(onClick = { onClickHistory() }) {
            Text("История транзакций")
        }

        Button(onClick = { onClickPortfolio() }) {
            Text("Ваш портфель")
        }
    }
}