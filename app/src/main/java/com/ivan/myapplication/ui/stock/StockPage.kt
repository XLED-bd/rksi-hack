package com.ivan.myapplication.ui.stock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.ivan.myapplication.ui.stock.stockchart.RandomStockChart
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun StockPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    id: String?,
) {
    viewModel.getStock(id!!)
    val stock: Stock by viewModel.stock.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Заголовок
        Text(
            text = stock.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Тикер: ${stock.symbol}",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Последняя цена: ${stock.last_price} ${stock.currency}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Доступные действия",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { viewModel.buyStock(stock.id, 1, stock.last_price) },
                    colors = ButtonDefaults.buttonColors(Color.Green),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Купить за ${stock.last_price} ${stock.currency}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { /* Обработчик продажи */ },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Продать",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "График цены",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            RandomStockChart()
        }
    }
}