package com.ivan.myapplication.ui.stock.stockchart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StockChart(
    prices: List<Float>,
    modifier: Modifier = Modifier
) {
    val maxPrice = prices.maxOrNull() ?: 1f
    val minPrice = prices.minOrNull() ?: 0f

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(2.dp, Color(0xFFE57373), shape = RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5)) // Светло-серый фон
    ) {
        val width = size.width
        val height = size.height

        val stepX = width / (prices.size - 1).coerceAtLeast(1)
        val rangeY = (maxPrice - minPrice).takeIf { it > 0 } ?: 1f

        val normalizedPrices = prices.map { (it - minPrice) / rangeY }

        for (i in 0 until normalizedPrices.size - 1) {
            val x1 = i * stepX
            val y1 = height - normalizedPrices[i] * height

            val x2 = (i + 1) * stepX
            val y2 = height - normalizedPrices[i + 1] * height

            drawLine(
                color = if (prices[i + 1] >= prices[i]) Color.Green else Color(0xFFE57373),
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = 4f
            )

            // Добавляем точки на пересечениях для визуального улучшения
            drawCircle(
                color = Color.Gray,
                radius = 4f,
                center = Offset(x1, y1)
            )
            if (i == normalizedPrices.size - 2) {
                drawCircle(
                    color = Color.Gray,
                    radius = 4f,
                    center = Offset(x2, y2)
                )
            }
        }
    }
}

@Composable
fun RandomStockChart() {
    val randomPrices = List(20) { (100..200).random().toFloat() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Stock Price Chart",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121), // Тёмно-серый цвет текста
            modifier = Modifier.padding(bottom = 12.dp)
        )

        StockChart(prices = randomPrices)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRandomStockChart() {
    RandomStockChart()
}

