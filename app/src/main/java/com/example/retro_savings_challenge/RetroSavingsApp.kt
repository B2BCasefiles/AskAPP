package com.example.retro_savings_challenge

import android.app.Application
import com.example.retro_savings_challenge.core.DeviceProfiler

class RetroSavingsApp : Application() {

    lateinit var deviceProfile: DeviceProfile

    override fun onCreate() {
        super.onCreate()
        deviceProfile = DeviceProfiler.getProfile(this)
    }
}
