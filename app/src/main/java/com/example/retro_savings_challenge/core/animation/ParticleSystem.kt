package com.example.retro_savings_challenge.core.animation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.painterResource
import com.example.retro_savings_challenge.R
import kotlinx.coroutines.android.awaitFrame

@Composable
fun ParticleSystem(
    modifier: Modifier = Modifier,
    manager: ParticleManager
) {
    val particles = remember { manager.particles }
    val coinPainter = painterResource(id = R.drawable.ic_coin_static)

    LaunchedEffect(Unit) {
        while (true) {
            val frameTimeMillis = awaitFrame() / 1_000_000f
            manager.update(frameTimeMillis)
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        particles.forEach { particle ->
            if (particle.isAlive) {
                translate(left = particle.position.x, top = particle.position.y) {
                    with(coinPainter) {
                        draw(
                            size = Size(64f * particle.scale, 64f * particle.scale),
                            alpha = particle.alpha
                        )
                    }
                }
            }
        }
    }
}
