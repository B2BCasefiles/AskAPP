package com.example.retro_savings_challenge.core

import android.app.ActivityManager
import android.content.Context
import com.example.retro_savings_challenge.DeviceProfile
import kotlin.math.roundToInt

object DeviceProfiler {

    // Thresholds in GB. Using slightly more than the integer to avoid floating point inaccuracies.
    private const val LOW_MEMORY_THRESHOLD_GB = 2.1
    private const val MEDIUM_MEMORY_THRESHOLD_GB = 4.1

    fun getProfile(context: Context): DeviceProfile {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
            ?: return DeviceProfile.MEDIUM // Default to medium if service is unavailable

        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalMemoryGB = memoryInfo.totalMem / (1024.0 * 1024.0 * 1024.0)

        return when {
            totalMemoryGB <= LOW_MEMORY_THRESHOLD_GB -> DeviceProfile.LOW
            totalMemoryGB <= MEDIUM_MEMORY_THRESHOLD_GB -> DeviceProfile.MEDIUM
            else -> DeviceProfile.HIGH
        }
    }
}
