package com.ivan.myapplication.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePage(modifier: Modifier, onClickHistory: () -> Unit, onClickPortfolio: () -> Unit) {
    Column(modifier){
        Spacer(Modifier.height(8.dp))
        Button(onClick = { onClickHistory() }, Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFFffffff),
            containerColor = Color(0xFFff0000)
            )) {
            Text("История транзакций")
        }

        Button(onClick = { onClickPortfolio() }, Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color(0xFFffffff),
                containerColor = Color(0xFFff0000)
            )) {
            Text("Ваш портфель")
        }
    }
}