package com.example.retro_savings_challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retro_savings_challenge.DeviceProfile
import com.example.retro_savings_challenge.ui.screens.ChallengeViewModel
import com.example.retro_savings_challenge.ui.screens.DashboardViewModel

class ViewModelFactory(
    private val deviceProfile: DeviceProfile
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(deviceProfile) as T
            }
            modelClass.isAssignableFrom(ChallengeViewModel::class.java) -> {
                ChallengeViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
