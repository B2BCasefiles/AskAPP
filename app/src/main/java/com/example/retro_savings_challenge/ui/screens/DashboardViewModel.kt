package com.example.retro_savings_challenge.ui.screens

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.core.Constants
import com.example.retro_savings_challenge.core.animation.ParticleManager
import com.example.retro_savings_challenge.data.model.Challenge
import com.example.retro_savings_challenge.data.model.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class DashboardViewModel(
    // No longer depends on the repository
    private val deviceProfile: DeviceProfile
) : ViewModel() {

    val particleManager = ParticleManager()

    private val _uiState: MutableStateFlow<DashboardUiState>
    val uiState: StateFlow<DashboardUiState>

    init {
        val challenges = listOf(
            Challenge("1", "52-Week Savings Challenge", "Save an increasing amount each week, starting with $1.", "A cool badge!", "Weekly", 1378.0),
            Challenge("2", "Round-Up Challenge", "Round up your daily purchases to the nearest dollar and save the change.", "Unlock a new theme!", "Daily", 100.0),
            Challenge("3", "No-Spend Weekend", "Try not to spend any money for an entire weekend.", "Bonus Points!", "Monthly", 0.0)
        )
        val initialSavings = 600f
        val initialProgress = (initialSavings / Constants.OVERALL_SAVINGS_GOAL).coerceIn(0f, 1f)

        _uiState = MutableStateFlow(
            DashboardUiState(
                challenges = challenges,
                currentProgress = initialProgress,
                totalSavings = initialSavings,
                deviceProfile = deviceProfile,
                isLoading = false
            )
        )
        uiState = _uiState.asStateFlow()
    }

    private var lastKnownSavings = _uiState.value.totalSavings

    fun onSavingsChanged(newSavings: Float, jarPosition: Offset) {
        if (newSavings > lastKnownSavings) {
            particleManager.emit(jarPosition.x, jarPosition.y, 50)
        }
        lastKnownSavings = newSavings
    }

    fun saveTransaction(amount: Double) {
        val currentSavings = _uiState.value.totalSavings
        val newSavings = currentSavings + amount.toFloat()
        _uiState.value = _uiState.value.copy(
            totalSavings = newSavings,
            currentProgress = (newSavings / Constants.OVERALL_SAVINGS_GOAL).coerceIn(0f, 1f)
        )
    }
}

data class DashboardUiState(
    val challenges: List<Challenge> = emptyList(),
    val currentProgress: Float = 0f,
    val totalSavings: Float = 0f,
    val isLoading: Boolean = false,
    val deviceProfile: DeviceProfile = DeviceProfile.MEDIUM
)
