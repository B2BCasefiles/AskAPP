package com.example.retro_savings_challenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retro_savings_challenge.data.repository.DashboardRepository
import com.example.retro_savings_challenge.ui.screens.DashboardViewModel

import com.example.retro_savings_challenge.DeviceProfile

class ViewModelFactory(
    private val repository: DashboardRepository,
    private val deviceProfile: DeviceProfile
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(repository, deviceProfile) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
