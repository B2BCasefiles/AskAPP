package com.example.retro_savings_challenge

import android.app.Application
import com.example.retro_savings_challenge.data.local.AppDatabase
import com.example.retro_savings_challenge.data.repository.DashboardRepository

import com.example.retro_savings_challenge.core.DeviceProfiler

class RetroSavingsApp : Application() {

    lateinit var deviceProfile: DeviceProfile

    // Using by lazy so the database and repository are only created when they're needed
    // for the first time.
    val database by lazy { AppDatabase.getDatabase(this) }
    val dashboardRepository by lazy {
        DashboardRepository(
            database.challengeDao(),
            database.participationDao(),
            database.transactionDao()
        )
    }

    override fun onCreate() {
        super.onCreate()
        deviceProfile = DeviceProfiler.getProfile(this)
    }
}
