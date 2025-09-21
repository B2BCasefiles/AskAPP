package com.example.retro_savings_challenge.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retro_savings_challenge.R
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

@Composable
fun SavingsJar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.width(200.dp).height(250.dp),
        shape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp, bottomStart = 20.dp, bottomEnd = 20.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
        tonalElevation = 4.dp,
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Pile of coins
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_coin_static),
                    contentDescription = "Coin",
                    tint = Color(0xFFFFD166),
                    modifier = Modifier.size(32.dp).offset(x = 8.dp, y = 4.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_coin_static),
                    contentDescription = "Coin",
                    tint = Color(0xFFFFD166),
                    modifier = Modifier.size(32.dp).offset(x = (-8).dp, y = (-4).dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_coin_static),
                    contentDescription = "Coin",
                    tint = Color(0xFFFFD166),
                    modifier = Modifier.size(32.dp)
                )
            }

            // Jar Label
            Text(
                text = "$12.34",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavingsJarPreview() {
    RetroSavingsChallengeTheme(darkTheme = true) {
        Box(modifier = Modifier.padding(16.dp)) {
            SavingsJar()
        }
    }
}
