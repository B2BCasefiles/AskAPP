package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retro_savings_challenge.ui.ViewModelFactory

@Composable
fun ChallengeDetailsScreen(
    challengeId: String,
    factory: ViewModelFactory
) {
    val viewModel: ChallengeViewModel = viewModel(factory = factory)
    val challengeState by viewModel.getChallenge(challengeId).collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        challengeState?.let { challenge ->
            Text(text = challenge.title, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Rules: ${challenge.rules}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Reward: ${challenge.reward}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Frequency: ${challenge.frequency}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Goal: $${challenge.goalAmount}", style = MaterialTheme.typography.bodyLarge)
        } ?: run {
            CircularProgressIndicator()
        }
    }
}
