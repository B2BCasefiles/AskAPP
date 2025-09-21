package com.example.retro_savings_challenge.ui.screens

import androidx.lifecycle.ViewModel
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

import com.example.retro_savings_challenge.DeviceProfile

class DashboardViewModel(
    repository: DashboardRepository,
    private val deviceProfile: DeviceProfile
) : ViewModel() {

    val uiState: StateFlow<DashboardUiState> = repository.getActiveChallenges()
        .map { challenges ->
            DashboardUiState(
                challenges = challenges,
                currentProgress = 0.75f, // Still hardcoded for now
                deviceProfile = deviceProfile
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardUiState(isLoading = true, deviceProfile = deviceProfile)
        )
}

data class DashboardUiState(
    val challenges: List<Challenge> = emptyList(),
    val currentProgress: Float = 0f,
    val isLoading: Boolean = false,
    val deviceProfile: DeviceProfile = DeviceProfile.MEDIUM // Default value
)
