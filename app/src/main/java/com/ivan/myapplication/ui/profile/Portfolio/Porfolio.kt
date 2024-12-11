package com.ivan.myapplication.ui.profile.Portfolio

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
import com.ivan.myapplication.model.Portfolio
import com.ivan.myapplication.viewModel.MainViewModel


@Composable
fun Portfolio(modifier: Modifier, viewModel: MainViewModel) {
    viewModel.getPortfolios()
    val portfolios by viewModel.portfolios.collectAsState()

    Box(modifier){
        LazyColumn {
            items(portfolios) { item ->
                StockCard(item)
            }
        }
    }
}

@Composable
fun StockCard(
    portfolio: Portfolio
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
            // Верхняя часть: название и символ акции
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = portfolio.stock_name,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(${portfolio.stock_symbol})",
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Current Price: \$${String.format("%.2f", portfolio.current_price)}",
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Amount: ${portfolio.amount}",
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Avg. Price: \$${portfolio.average_purchase_price}",
                    color = Color.Gray,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                val profitLoss = (portfolio.current_price - portfolio.stock_last_price) * portfolio.amount
                val profitLossColor = if (profitLoss >= 0) Color.Green else Color.Red
                Text(
                    text = String.format("P/L: \$%.2f", profitLoss),
                    color = profitLossColor
                )
            }
        }
    }
}