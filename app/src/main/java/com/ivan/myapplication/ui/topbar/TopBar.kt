package com.ivan.myapplication.ui.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Topbar(text: String){
    Box(Modifier.background(Color.White).padding(top = 30.dp).fillMaxWidth().height(50.dp)){
        Text(text, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}