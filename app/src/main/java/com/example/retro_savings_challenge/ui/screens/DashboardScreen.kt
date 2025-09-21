package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.core.animation.ParticleSystem
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.ui.ViewModelFactory
import com.example.retro_savings_challenge.ui.components.AdaptiveProgressRing
import com.example.retro_savings_challenge.ui.components.SavingsJar
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

@Composable
fun DashboardScreen(
    factory: ViewModelFactory,
    onNavigateToChallengeBrowser: () -> Unit,
    onNavigateToAddTransaction: () -> Unit,
    onNavigateToRewards: () -> Unit
) {
    val viewModel: DashboardViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()
    var jarPosition by remember { mutableStateOf(Offset.Zero) }

    // This effect will run whenever totalSavings changes
    LaunchedEffect(uiState.totalSavings) {
        if (jarPosition != Offset.Zero) {
            viewModel.onSavingsChanged(uiState.totalSavings, jarPosition)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        DashboardContent(
            uiState = uiState,
            onNavigateToChallengeBrowser = onNavigateToChallengeBrowser,
            onNavigateToAddTransaction = onNavigateToAddTransaction,
            onNavigateToRewards = onNavigateToRewards,
            onJarPositioned = { jarPosition = it }
        )
        ParticleSystem(manager = viewModel.particleManager)
    }
}

@Composable
fun DashboardContent(
    uiState: DashboardUiState,
    onNavigateToChallengeBrowser: () -> Unit,
    onNavigateToAddTransaction: () -> Unit,
    onNavigateToRewards: () -> Unit,
    onJarPositioned: (Offset) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "My Dashboard", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            AdaptiveProgressRing(progress = uiState.currentProgress, deviceProfile = uiState.deviceProfile)
            Spacer(modifier = Modifier.height(32.dp))
            SavingsJar(
                totalSavings = uiState.totalSavings,
                modifier = Modifier.onGloballyPositioned {
                    val rootPosition = it.positionInRoot()
                    val center = Offset(
                        rootPosition.x + (it.size.width / 2),
                        rootPosition.y + (it.size.height / 2)
                    )
                    onJarPositioned(center)
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Active Challenges", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            if (uiState.isLoading) {
                Text("Loading challenges...")
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.challenges) { challenge ->
                        Text(text = challenge.title)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = onNavigateToChallengeBrowser) {
                    Text("Challenges")
                }
                Button(onClick = onNavigateToAddTransaction) {
                    Text("Add Funds")
                }
                Button(onClick = onNavigateToRewards) {
                    Text("Rewards")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    RetroSavingsChallengeTheme(darkTheme = true) {
        val previewState = DashboardUiState(
            challenges = listOf(
                Challenge("1", "Preview Challenge 1", "", "", "", 0.0),
                Challenge("2", "Preview Challenge 2", "", "", "", 0.0)
            ),
            currentProgress = 0.6f,
            totalSavings = 600f
        )
        DashboardContent(
            uiState = previewState,
            onNavigateToChallengeBrowser = {},
            onNavigateToAddTransaction = {},
            onNavigateToRewards = {},
            onJarPositioned = {}
        )
    }
}
