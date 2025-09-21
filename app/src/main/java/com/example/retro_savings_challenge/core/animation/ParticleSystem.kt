package com.example.retro_savings_challenge.core.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.android.awaitFrame

@Composable
fun ParticleSystem(
    modifier: Modifier = Modifier,
    manager: ParticleManager
) {
    val particles = remember { manager.particles }

    LaunchedEffect(Unit) {
        while (true) {
            val frameTimeMillis = awaitFrame() / 1_000_000f
            manager.update(frameTimeMillis)
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        particles.forEach { particle ->
            if (particle.isAlive) {
                drawCircle(
                    color = particle.color,
                    center = particle.position,
                    radius = 12f * particle.scale,
                    alpha = particle.alpha
                )
            }
        }
    }
}
