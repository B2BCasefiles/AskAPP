package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retro_savings_challenge.ui.ViewModelFactory
import com.example.retro_savings_challenge.ui.components.ChallengeCard

@Composable
fun ChallengeBrowserScreen(
    factory: ViewModelFactory,
    onNavigateToDetails: (String) -> Unit
) {
    val viewModel: ChallengeViewModel = viewModel(factory = factory)
    val challenges by viewModel.challenges.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Explore Challenges", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(challenges) { challenge ->
                ChallengeCard(
                    challenge = challenge,
                    onClick = { onNavigateToDetails(challenge.id) }
                )
            }
        }
    }
}
