package com.ivan.myapplication.ui.profile.history

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ivan.myapplication.model.StockHistory
import com.ivan.myapplication.viewModel.MainViewModel

@Composable
fun History(modifier: Modifier, viewModel: MainViewModel) {
    val history by viewModel.history.collectAsState()
    viewModel.getHistory()

    LazyColumn (modifier){
        items(history.reversed()){ item ->
            StockHistoryCard(item)
        }
    }
}

@Composable
fun StockHistoryCard(
    history: StockHistory,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).border(1.dp, Color.Red, RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = history.stock_name,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${history.stock_symbol})",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Средняя часть: тип транзакции и дата
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                val transactionColor = if (history.type == "Buy") Color.Green else Color.Red
                Text(
                    text = history.type,
                    color = transactionColor,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = history.created_at,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Amount: ${history.amount}",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Price: \$${String.format("%.2f", history.price)}",
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Last Price: \$${String.format("%.2f", history.stock_last_price)}",
                    color = Color.Gray
                )
            }
        }
    }
}