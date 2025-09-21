package com.example.retro_savings_challenge.data.preferences

import android.content.Context
import android.content.SharedPreferences

class OnboardingManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var hasCompletedOnboarding: Boolean
        get() = prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false)
        set(value) = prefs.edit().putBoolean(KEY_ONBOARDING_COMPLETE, value).apply()

    companion object {
        private const val PREFS_NAME = "retro_savings_prefs"
        private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
    }
}
