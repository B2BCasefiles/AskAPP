package com.example.retro_savings_challenge.ui.screens

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.core.Constants
import com.example.retro_savings_challenge.core.animation.ParticleManager
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Transaction
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID

class DashboardViewModel(
    private val repository: DashboardRepository,
    private val deviceProfile: DeviceProfile
) : ViewModel() {

    val particleManager = ParticleManager()
    private var lastKnownSavings = 0f

    val uiState: StateFlow<DashboardUiState> = combine(
        repository.getActiveChallenges(),
        repository.getTotalSavings()
    ) { challenges, totalSavings ->
        val currentSavings = totalSavings?.toFloat() ?: 0f
        val progress = (currentSavings / Constants.OVERALL_SAVINGS_GOAL).coerceIn(0f, 1f)
        DashboardUiState(
            challenges = challenges,
            currentProgress = progress,
            totalSavings = currentSavings,
            deviceProfile = deviceProfile,
            isLoading = false
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardUiState(isLoading = true, deviceProfile = deviceProfile)
    )

    fun onSavingsChanged(newSavings: Float, jarPosition: Offset) {
        if (newSavings > lastKnownSavings) {
            // Savings have increased, trigger the particle effect
            particleManager.emit(jarPosition.x, jarPosition.y, 50)
        }
        lastKnownSavings = newSavings
    }

    fun saveTransaction(amount: Double) {
        viewModelScope.launch {
            val transaction = Transaction(
                id = UUID.randomUUID().toString(),
                userId = Constants.USER_ID,
                amount = amount,
                date = System.currentTimeMillis(),
                type = "manual"
            )
            repository.saveTransaction(transaction)
        }
    }
}

data class DashboardUiState(
    val challenges: List<Challenge> = emptyList(),
    val currentProgress: Float = 0f,
    val totalSavings: Float = 0f,
    val isLoading: Boolean = false,
    val deviceProfile: DeviceProfile = DeviceProfile.MEDIUM
)
