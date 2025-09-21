package com.example.retro_savings_challenge.ui.screens

import androidx.lifecycle.ViewModel
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Participation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

data class ChallengeDetailsUiState(
    val challenge: Challenge? = null,
    val isJoined: Boolean = false,
    val isLoading: Boolean = true
)

class ChallengeViewModel() : ViewModel() {

    private val allChallenges = listOf(
        Challenge("1", "52-Week Savings Challenge", "Save an increasing amount each week, starting with $1.", "A cool badge!", "Weekly", 1378.0),
        Challenge("2", "Round-Up Challenge", "Round up your daily purchases to the nearest dollar and save the change.", "Unlock a new theme!", "Daily", 100.0),
        Challenge("3", "No-Spend Weekend", "Try not to spend any money for an entire weekend.", "Bonus Points!", "Monthly", 0.0)
    )

    // For the browser screen
    val challenges: StateFlow<List<Challenge>> = MutableStateFlow(allChallenges).asStateFlow()

    // For the details screen
    private val _detailsState = MutableStateFlow(ChallengeDetailsUiState())
    val detailsState: StateFlow<ChallengeDetailsUiState> = _detailsState.asStateFlow()

    private val participations = mutableSetOf<String>()

    fun loadChallengeDetails(challengeId: String) {
        val challenge = allChallenges.find { it.id == challengeId }
        _detailsState.value = ChallengeDetailsUiState(
            challenge = challenge,
            isJoined = participations.contains(challengeId),
            isLoading = false
        )
    }

    fun toggleParticipation(challengeId: String) {
        val isJoined = participations.contains(challengeId)
        if (isJoined) {
            participations.remove(challengeId)
        } else {
            participations.add(challengeId)
        }
        // Update the details state to reflect the change
        _detailsState.value = _detailsState.value.copy(isJoined = !isJoined)
    }
}
