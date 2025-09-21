package com.example.retro_savings_challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import com.example.retro_savings_challenge.ui.screens.DashboardViewModel

import com.example.retro_savings_challenge.DeviceProfile

import com.example.retro_savings_challenge.ui.screens.ChallengeViewModel

class ViewModelFactory(
    private val repository: DashboardRepository,
    private val deviceProfile: DeviceProfile
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(repository, deviceProfile) as T
            }
            modelClass.isAssignableFrom(ChallengeViewModel::class.java) -> {
                ChallengeViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
