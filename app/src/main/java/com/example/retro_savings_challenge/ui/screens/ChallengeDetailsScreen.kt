package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retro_savings_challenge.ui.ViewModelFactory

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ChallengeDetailsScreen(
    challengeId: String,
    factory: ViewModelFactory,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val viewModel: ChallengeViewModel = viewModel(factory = factory)
    // Load the details for the specific challenge when the screen is composed
    LaunchedEffect(challengeId) {
        viewModel.loadChallengeDetails(challengeId)
    }
    val uiState by viewModel.detailsState.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            uiState.challenge?.let { challenge ->
                Column(modifier = with(sharedTransitionScope) {
                    Modifier
                        .fillMaxSize()
                        .sharedElement(
                            rememberSharedContentState(key = "card-${challenge.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                }) {
                    Text(text = challenge.title, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Rules: ${challenge.rules}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    AnimatedTimeline()
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Reward: ${challenge.reward}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Frequency: ${challenge.frequency}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Goal: $${challenge.goalAmount}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { viewModel.toggleParticipation(challenge.id) },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(if (uiState.isJoined) "Leave Challenge" else "Join Challenge")
                    }
                }
            } ?: run {
                Text("Challenge not found.", modifier = Modifier.align(Alignment.Center))
            }
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.drawscope.Stroke
import com.example.retro_savings_challenge.ui.theme.NeonCyan
import com.example.retro_savings_challenge.ui.theme.NeonMagenta
import kotlinx.coroutines.launch

        }
    }
}

@Composable
private fun AnimatedTimeline() {
    val progress = remember { Animatable(0f) }
    val nodeScales = remember { (0..5).map { Animatable(0f) } }

    LaunchedEffect(Unit) {
        progress.animateTo(1f, animationSpec = tween(durationMillis = 1000))
        // Animate nodes popping in sequentially
        nodeScales.forEachIndexed { index, scale ->
            launch {
                kotlinx.coroutines.delay(index * 150L)
                scale.animateTo(1f, animationSpec = tween(300))
            }
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f, 10f), 0f)
        val nodes = 5
        val nodeRadius = 12f
        val endX = (size.width - nodeRadius * 2) * progress.value + nodeRadius

        // Draw the dashed line
        drawLine(
            color = NeonCyan,
            start = Offset(nodeRadius, size.height / 2),
            end = Offset(endX, size.height / 2),
            strokeWidth = 5f,
            pathEffect = pathEffect
        )

        // Draw the nodes
        for (i in 0..nodes) {
            val x = (size.width / nodes) * i
            drawCircle(
                color = NeonMagenta,
                radius = nodeRadius * nodeScales[i].value,
                center = Offset(x.coerceIn(nodeRadius, size.width - nodeRadius), size.height / 2)
            )
        }
    }
}
