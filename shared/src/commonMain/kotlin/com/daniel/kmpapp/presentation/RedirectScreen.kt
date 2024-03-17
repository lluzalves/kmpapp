package com.daniel.kmpapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniel.kmpapp.openWebPage


@Composable
fun RedirectScreen() {
    Scaffold(
        topBar = {
            TopBar()
        }) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .width(240.dp)
                    .height(40.dp),
                onClick = { openWebPage().openUrl("http://www.terra.com.br") }) {
                Text(text = "Redirect to webpage")
            }
        }
    }
}

@Composable
fun TopBar() {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Kmp Example expected/actual",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}
