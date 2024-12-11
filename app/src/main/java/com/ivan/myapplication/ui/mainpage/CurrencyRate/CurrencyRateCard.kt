package com.ivan.myapplication.ui.mainpage.CurrencyRate

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CurrencyRatesCard(
    currencies: List<Currency>, // Данные валют
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.Red),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Заголовок и кнопка "Ещё"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Курсы",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = "Ещё",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TabItem(title = "Валют", isSelected = true)
                TabItem(title = "Металлов", isSelected = false)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Купить",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Продать",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Список валют
            currencies.forEach { currency ->
                CurrencyRow(currency = currency)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = if (isSelected) Color.Black else Color.Gray,
        modifier = Modifier
            .background(
                if (isSelected) Color.LightGray else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun CurrencyRow(currency: Currency) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.LightGray, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Currency Icon",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = currency.code, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = currency.name, fontSize = 14.sp, color = Color.Gray)
            }
        }

        Row {
            Text(
                text = currency.buyPrice,
                fontSize = 16.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            Text(
                text = currency.sellPrice,
                fontSize = 16.sp
            )
        }
    }
}

data class Currency(
    val code: String,
    val name: String,
    val buyPrice: String,
    val sellPrice: String
)