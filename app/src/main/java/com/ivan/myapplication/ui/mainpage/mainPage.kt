package com.ivan.myapplication.ui.mainpage

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.model.User
import com.ivan.myapplication.viewModel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(modifier: Modifier, viewModel: MainViewModel, onLogout: () -> Unit) {
    viewModel.getStocks()
    val stocks: List<Stock> by viewModel.stocks.collectAsState()

    viewModel.getUser()
    val user: User by viewModel.user.collectAsState()

    Box(modifier){
        Text("Добрый Жень")
    }
}