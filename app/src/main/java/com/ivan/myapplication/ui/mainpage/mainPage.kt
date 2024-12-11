package com.ivan.myapplication.ui.mainpage

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivan.myapplication.R
import com.ivan.myapplication.model.Stock
import com.ivan.myapplication.model.User
import com.ivan.myapplication.ui.mainpage.CurrencyRate.Currency
import com.ivan.myapplication.ui.mainpage.CurrencyRate.CurrencyRatesCard
import com.ivan.myapplication.viewModel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onLogout: () -> Unit,
    onInvestClick: () -> Unit
) {
    viewModel.getUser()
    val user: User by viewModel.user.collectAsState()

    viewModel.getBalance()
    val balance: Double by viewModel.balance.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Добро пожаловать!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )

                Card(
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(2.dp, Color.Red),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .height(350.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Текущая стоимость Вашего\nинвестиционного портфеля составляет:",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(28.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "₽ ${balance.toInt()}",
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "▲",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Green
                            )
                        }

                        Spacer(modifier = Modifier.height(120.dp))

                        Button(
                            onClick = onInvestClick,
                            colors = ButtonDefaults.buttonColors(Color.Red),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "К инвестициям",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // Дополнительная информация
        Spacer(modifier = Modifier.height(52.dp))

        val currencies = listOf(
            Currency("CNY", "Юань", "6.50", "6.70"),
            Currency("USD", "Доллар", "76.30", "76.50"),
            Currency("AED", "Дирхам", "20.30", "20.50"),
            Currency("KZT", "Тенге", "0.15", "0.16")
        )

        CurrencyRatesCard(currencies = currencies)

//        Text(
//            text = "Информация о пользователе",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        // Пример отображения информации о пользователе
//        Text(
//            text = "Имя: ${user.id}\nEmail: ${user.email}",
//            fontSize = 16.sp,
//            textAlign = TextAlign.Start,
//            modifier = Modifier.padding(horizontal = 16.dp)
//        )
    }
}