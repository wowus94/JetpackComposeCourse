package ru.vlyashuk.jetpackcomposecourse

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ProfileCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(58.dp)
                    .clip(shape = CircleShape)
                    .background(color = Color.White)
                    .padding(4.dp),
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = ""
            )
            UsersStatistics("Posts", "123")
            UsersStatistics("Followers", "44M")
            UsersStatistics("Following", "70")
        }
        Text(
            text = "Instagramm",
            modifier = Modifier
                .padding(top = 40.dp, start = 16.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        Text(
            text = "#Hashtag",
            modifier = Modifier.padding(start = 16.dp),
            fontStyle = FontStyle.Italic,
            fontSize = 18.sp
        )
        Text(
            text = "www.sitetest.com",
            modifier = Modifier.padding(16.dp),
            fontFamily = FontFamily.Serif
        )
        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "Hello Click", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun UsersStatistics(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = value,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = title,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
    }
}