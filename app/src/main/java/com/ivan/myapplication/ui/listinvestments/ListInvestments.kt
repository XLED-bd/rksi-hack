package com.ivan.myapplication.ui.listinvestments

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun ListInvestments(modifier: Modifier, viewModel: MainViewModel, onClickStock: (String) -> Unit){
    viewModel.getStocks()
    val stocks: List<Stock> by viewModel.stocks.collectAsState()


    Box(modifier){
        LazyColumn {
            items(stocks.size) { stock ->
                ItemStock(stocks[stock], onClickStock = {
                    onClickStock(it)
                })
            }
        }
    }
}

@Composable
fun ItemStock(stock: Stock, onClickStock: (String) -> Unit){
    Card(shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(stock.toString(), Modifier.padding(16.dp))
        Button(onClick = { onClickStock(stock.id) }) {
            Text("Посмотреть")
        }
    }
}

