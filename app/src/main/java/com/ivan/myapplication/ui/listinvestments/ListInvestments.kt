package com.ivan.myapplication.ui.listinvestments

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun ListInvestments(modifier: Modifier, viewModel: MainViewModel, onClickStock: (String) -> Unit){
    viewModel.getStocks()
    val stocks: List<Stock> by viewModel.stocks.collectAsState()


    Box(modifier){
        LazyColumn {
            items(stocks.size) { stock ->
                StockCard(stocks[stock], onClickStock = {
                    onClickStock(it)
                })
            }
        }
    }
}

@Composable
fun StockCard(
    stock: Stock,
    onClickStock: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Column {
            // Верхняя строка: название и символ
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stock.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "(${stock.symbol})",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Last Price: ${stock.currency} ${String.format("%.2f", stock.last_price)}",
                    fontSize = 16.sp,
                    color = Color.Blue,
                    modifier = Modifier.weight(1f)
                )
            }
            Button(onClick = { onClickStock(stock.id) }) {
                Text("Посмотреть")
            }
        }
    }
}

