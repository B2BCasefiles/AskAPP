package com.example.retro_savings_challenge.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ChallengeViewModel(private val repository: DashboardRepository) : ViewModel() {

    val challenges: StateFlow<List<Challenge>> = repository.getActiveChallenges()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun getChallenge(id: String): StateFlow<Challenge?> {
        return repository.getChallengeById(id)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
    }
}
