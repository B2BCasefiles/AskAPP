package com.example.retro_savings_challenge.core.animation

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class Particle(
    var position: Offset = Offset.Zero,
    var velocity: Offset = Offset.Zero,
    var color: Color = Color.White,
    var alpha: Float = 1f,
    var scale: Float = 1f,
    var lifetime: Float = 0f,
    var isAlive: Boolean = false
) {
    fun reset() {
        position = Offset.Zero
        velocity = Offset.Zero
        color = Color.White
        alpha = 1f
        scale = 1f
        lifetime = 0f
        isAlive = false
    }
}
