package com.example.retro_savings_challenge.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.R

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember

@Composable
fun AdaptiveProgressRing(
    progress: Float, // 0f..1f
    deviceProfile: DeviceProfile // HIGH / MEDIUM / LOW
) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = if (deviceProfile == DeviceProfile.LOW) tween(300) else spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "Progress Animation"
    )
    val strokeWidthMultiplier = remember { Animatable(1f) }

    LaunchedEffect(progress) {
        if (deviceProfile != DeviceProfile.LOW) {
            strokeWidthMultiplier.snapTo(1.5f)
            strokeWidthMultiplier.animateTo(1f, animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = 300f))
        }
    }

    Box(
        modifier = Modifier
            .size(if (deviceProfile == DeviceProfile.LOW) 120.dp else 200.dp)
            .graphicsLayer { // GPU friendly transforms
                shadowElevation = if (deviceProfile == DeviceProfile.LOW) 2f else 8f
                shape = RoundedCornerShape(16.dp)
                clip = true
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val baseStrokeWidth = size.minDimension * 0.12f
            val stroke = Stroke(width = baseStrokeWidth * strokeWidthMultiplier.value, cap = StrokeCap.Round)
            // background ring
            drawArc(
                color = Color(0xFF2B2B2B),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = stroke
            )
            // animated progress
            drawArc(
                color = if (deviceProfile == DeviceProfile.LOW) Color.Cyan else Color.Magenta,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress.value,
                useCenter = false,
                style = stroke
            )
        }

        // center content: use Lottie for HIGH/MEDIUM, static for LOW
        if (deviceProfile != DeviceProfile.LOW) {
            val composition by rememberLottieComposition(LottieCompositionSpec.Asset("coin_pulse.json"))
            LottieAnimation(
                composition = composition,
                modifier = Modifier.align(Alignment.Center).size(64.dp),
                iterations = Int.MAX_VALUE
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.ic_coin_static),
                contentDescription = "coin",
                modifier = Modifier.align(Alignment.Center).size(36.dp)
            )
        }
    }
}
