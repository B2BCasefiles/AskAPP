package com.example.retro_savings_challenge.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun RewardsScreen() {
    var showMilestone by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showMilestone = true }) {
            Text("Trigger Milestone!")
        }

        AnimatedVisibility(visible = showMilestone) {
            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("milestone.json"))
            LottieAnimation(
                composition = composition,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
