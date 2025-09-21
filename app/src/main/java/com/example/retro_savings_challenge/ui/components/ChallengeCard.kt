package com.example.retro_savings_challenge.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

@Composable
fun ChallengeCard(
    challenge: Challenge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val rotationX = remember { Animatable(0f) }
    val rotationY = remember { Animatable(0f) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                this.rotationX = rotationX.value
                this.rotationY = rotationY.value
            }
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        val (x, y) = down.position
                        val (width, height) = size
                        val rotationYFactor = (x - width / 2) / (width / 2)
                        val rotationXFactor = -(y - height / 2) / (height / 2)

                        scope.launch {
                            rotationX.animateTo(rotationXFactor * 15f, spring(stiffness = 300f))
                            rotationY.animateTo(rotationYFactor * 15f, spring(stiffness = 300f))
                        }

                        waitForUpOrCancellation()
                        scope.launch {
                            rotationX.animateTo(0f, spring(stiffness = 300f))
                            rotationY.animateTo(0f, spring(stiffness = 300f))
                        }
                    }
                }
            }
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = challenge.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = challenge.rules, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
fun ChallengeCardPreview() {
    RetroSavingsChallengeTheme {
        ChallengeCard(
            challenge = Challenge(
                id = "1",
                title = "52-Week Savings Challenge",
                rules = "Save an increasing amount each week, starting with $1.",
                reward = "A cool badge!",
                frequency = "Weekly",
                goalAmount = 1378.0
            ),
            modifier = Modifier.padding(16.dp),
            onClick = {}
        )
    }
}
