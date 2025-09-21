package com.example.retro_savings_challenge.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro_savings_challenge.core.Constants
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Participation
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

data class ChallengeDetailsUiState(
    val challenge: Challenge? = null,
    val isJoined: Boolean = false,
    val isLoading: Boolean = true
)

class ChallengeViewModel(private val repository: DashboardRepository) : ViewModel() {

    // For the browser screen
    val challenges: StateFlow<List<Challenge>> = repository.getActiveChallenges()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    // For the details screen
    private val _detailsState = MutableStateFlow(ChallengeDetailsUiState())
    val detailsState: StateFlow<ChallengeDetailsUiState> = _detailsState.asStateFlow()

    fun loadChallengeDetails(challengeId: String) {
        viewModelScope.launch {
            combine(
                repository.getChallengeById(challengeId),
                repository.getParticipation(Constants.USER_ID, challengeId)
            ) { challenge, participation ->
                ChallengeDetailsUiState(
                    challenge = challenge,
                    isJoined = participation != null,
                    isLoading = false
                )
            }.collect { state ->
                _detailsState.value = state
            }
        }
    }

    fun toggleParticipation(challengeId: String) {
        viewModelScope.launch {
            if (detailsState.value.isJoined) {
                repository.leaveChallenge(challengeId, Constants.USER_ID)
            } else {
                val participation = Participation(
                    id = UUID.randomUUID().toString(),
                    userId = Constants.USER_ID,
                    challengeId = challengeId,
                    progress = 0.0,
                    lastUpdate = System.currentTimeMillis()
                )
                repository.joinChallenge(participation)
            }
        }
    }
}
