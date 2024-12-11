package com.ivan.myapplication.ui.stock

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.ui.stock.stockchart.RandomStockChart
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun StockPage(modifier: Modifier, viewModel: MainViewModel, id: String?) {
    viewModel.getStock(id!!)
    val stock: Stock by viewModel.stock.collectAsState()

    Column(modifier){
        Text(stock.toString())
        Button(onClick = { viewModel.buyStocks(stock.id, 1, stock.last_price) })
        { Text("Купить")}
        Spacer(Modifier.height(8.dp))
        RandomStockChart()
    }
}