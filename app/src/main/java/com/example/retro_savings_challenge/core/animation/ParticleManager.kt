package com.example.retro_savings_challenge.core.animation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.geometry.Offset
import com.example.retro_savings_challenge.ui.theme.NeonCyan
import com.example.retro_savings_challenge.ui.theme.NeonMagenta
import com.example.retro_savings_challenge.ui.theme.RetroYellow
import kotlin.random.Random

class ParticleManager(poolSize: Int = 100) {

    val particles = mutableStateListOf<Particle>()
    private val gravity = 9.8f * 100f

    init {
        repeat(poolSize) {
            particles.add(Particle())
        }
    }

    fun update(frameTimeMillis: Float) {
        val delta = frameTimeMillis / 1000f
        particles.forEach { particle ->
            if (particle.isAlive) {
                particle.velocity = particle.velocity.copy(y = particle.velocity.y + gravity * delta)
                particle.position += particle.velocity * delta
                particle.lifetime -= delta
                particle.alpha = (particle.lifetime / 2f).coerceIn(0f, 1f)

                if (particle.lifetime <= 0) {
                    particle.isAlive = false
                }
            }
        }
    }

    fun emit(x: Float, y: Float, count: Int) {
        var emittedCount = 0
        particles.forEach {
            if (!it.isAlive && emittedCount < count) {
                it.reset()
                it.isAlive = true
                it.position = Offset(x, y)
                it.velocity = Offset(
                    x = Random.nextFloat() * 400f - 200f,
                    y = -Random.nextFloat() * 600f - 300f
                )
                it.lifetime = 2f
                it.color = listOf(NeonCyan, NeonMagenta, RetroYellow).random()
                it.scale = Random.nextFloat() * 0.5f + 0.5f
                emittedCount++
            }
        }
    }
}
