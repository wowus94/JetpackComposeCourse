package ru.vlyashuk.jetpackcomposecourse.other

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimesTable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        for (i in 1 until 10) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                for (j in 1 until 10) {
                    val backgroundColor = if ((j + i) % 2 == 0) Color.Yellow else Color.White
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(backgroundColor)
                            .weight(1f)
                            .border(width = 1.dp, color = Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "${j * i}")
                    }
                }
            }
        }
    }
}