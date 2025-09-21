package com.example.retro_savings_challenge.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme
import com.example.retro_savings_challenge.ui.theme.NeonCyan
import com.example.retro_savings_challenge.ui.theme.NeonMagenta
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onOnboardingFinished: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnboardingPage1Animated(pagerState)
                1 -> OnboardingPage2Animated()
                2 -> OnboardingPage(
                    title = "Ready to Start?",
                    text = "Let's begin your first challenge.",
                    buttonText = "Let's Go!",
                    onButtonClick = onOnboardingFinished
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingPage1Animated(pagerState: PagerState) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = pagerState.currentPageOffsetFraction
                translationX = pageOffset * size.width * 0.5f
            }
            .background(NeonMagenta.copy(alpha = 0.3f))
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = pagerState.currentPageOffsetFraction
                translationX = pageOffset * size.width * 0.7f
            }
            .background(NeonCyan.copy(alpha = 0.3f))
        )
        OnboardingPage(
            title = "Welcome to Retro Savings!",
            text = "Turn your spare change into a fun, retro-themed challenge."
        )
    }
}

@Composable
fun OnboardingPage2Animated() {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("How It Works", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("how_it_works.json"))
        LottieAnimation(
            composition = composition,
            iterations = Int.MAX_VALUE,
            modifier = Modifier.height(200.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text("Link a funding source or round up purchases. Watch your savings grow with cool animations!", textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun OnboardingPage(
    title: String,
    text: String,
    buttonText: String? = null,
    onButtonClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Text(text = text, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
        if (buttonText != null && onButtonClick != null) {
            Spacer(Modifier.height(32.dp))
            Button(onClick = onButtonClick) {
                Text(buttonText)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    RetroSavingsChallengeTheme {
        OnboardingScreen(onOnboardingFinished = {})
    }
}
