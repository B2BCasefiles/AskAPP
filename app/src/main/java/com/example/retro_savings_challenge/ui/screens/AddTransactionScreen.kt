package com.example.retro_savings_challenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

@Composable
fun AddTransactionScreen(
    factory: ViewModelFactory,
    onSaveComplete: () -> Unit
) {
    val viewModel: DashboardViewModel = viewModel(factory = factory)
    var amount by remember { mutableStateOf("") }
    val amountValue = amount.toDoubleOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Add a Manual Transaction", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(32.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberDecimal),
            singleLine = true
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                amountValue?.let {
                    viewModel.saveTransaction(it)
                    onSaveComplete()
                }
            },
            enabled = amountValue != null && amountValue > 0
        ) {
            Text("Save Transaction")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddTransactionScreenPreview() {
    RetroSavingsChallengeTheme {
        // This preview is not ideal as it needs a real ViewModel.
        // For a real project, we would create a fake ViewModel for previews.
        AddTransactionScreen(
            factory = ViewModelFactory(
                DashboardRepository(FakeChallengeDao(), FakeParticipationDao(), FakeTransactionDao()),
                DeviceProfile.MEDIUM
            ),
            onSaveComplete = {}
        )
    }
}

// Fake DAOs for previewing
class FakeChallengeDao : com.example.retro_savings_challenge.data.local.ChallengeDao {
    override fun getAllChallenges(): kotlinx.coroutines.flow.Flow<List<com.example.retro_savings_challenge.data.model.Challenge>> = kotlinx.coroutines.flow.flowOf(emptyList())
    override fun getChallengeById(id: String): kotlinx.coroutines.flow.Flow<com.example.retro_savings_challenge.data.model.Challenge> = kotlinx.coroutines.flow.flowOf()
    override suspend fun insertAll(challenges: List<com.example.retro_savings_challenge.data.model.Challenge>) {}
    override suspend fun insert(challenge: com.example.retro_savings_challenge.data.model.Challenge) {}
    override suspend fun deleteAll() {}
}
class FakeParticipationDao : com.example.retro_savings_challenge.data.local.ParticipationDao {
    override fun getParticipationsForUser(userId: String): kotlinx.coroutines.flow.Flow<List<com.example.retro_savings_challenge.data.model.Participation>> = kotlinx.coroutines.flow.flowOf(emptyList())
    override fun getParticipation(userId: String, challengeId: String): kotlinx.coroutines.flow.Flow<com.example.retro_savings_challenge.data.model.Participation?> = kotlinx.coroutines.flow.flowOf(null)
    override suspend fun insert(participation: com.example.retro_savings_challenge.data.model.Participation) {}
    override suspend fun delete(challengeId: String, userId: String) {}
    override suspend fun updateProgress(participationId: String, newProgress: Double) {}
}
class FakeTransactionDao : com.example.retro_savings_challenge.data.local.TransactionDao {
    override fun getAllTransactions(): kotlinx.coroutines.flow.Flow<List<com.example.retro_savings_challenge.data.model.Transaction>> = kotlinx.coroutines.flow.flowOf(emptyList())
    override fun getTotalSavings(): kotlinx.coroutines.flow.Flow<Double?> = kotlinx.coroutines.flow.flowOf(0.0)
    override suspend fun insert(transaction: com.example.retro_savings_challenge.data.model.Transaction) {}
}
