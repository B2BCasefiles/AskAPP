package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retro_savings_challenge.AdaptiveProgressRing
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retro_savings_challenge.ui.components.SavingsJar

import com.example.retro_savings_challenge.ui.ViewModelFactory

import com.example.retro_savings_challenge.data.model.Challenge

@Composable
fun DashboardScreen(factory: ViewModelFactory) {
    val viewModel: DashboardViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()
    DashboardContent(uiState = uiState)
}

@Composable
fun DashboardContent(uiState: DashboardUiState) {
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
            SavingsJar()
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Active Challenges", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            if (uiState.isLoading) {
                Text("Loading challenges...")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.challenges) { challenge ->
                        Text(text = challenge.title)
                    }
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
            currentProgress = 0.6f
        )
        DashboardContent(uiState = previewState)
    }
}
