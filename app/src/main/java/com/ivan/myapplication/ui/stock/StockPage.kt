package com.ivan.myapplication.ui.stock

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun StockPage(modifier: Modifier, viewModel: MainViewModel, id: String?) {
    viewModel.getStock(id!!)
    val stock: Stock by viewModel.stock.collectAsState()

    Box(modifier){
        Text(stock.toString())
    }
}