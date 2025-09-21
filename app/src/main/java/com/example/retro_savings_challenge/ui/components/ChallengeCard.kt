package com.example.retro_savings_challenge.ui.components

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

@Composable
fun ChallengeCard(challenge: Challenge, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
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
            modifier = Modifier.padding(16.dp)
        )
    }
}
