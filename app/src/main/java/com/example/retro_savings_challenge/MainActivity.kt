package com.example.retro_savings_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retro_savings_challenge.ui.screens.DashboardScreen
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

import com.example.retro_savings_challenge.ui.ViewModelFactory

import androidx.navigation.compose.rememberNavController
import com.example.retro_savings_challenge.data.preferences.OnboardingManager
import com.example.retro_savings_challenge.navigation.AppNavHost
import com.example.retro_savings_challenge.navigation.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = application as RetroSavingsApp
        val viewModelFactory = ViewModelFactory(
            application.dashboardRepository,
            application.deviceProfile
        )
        val onboardingManager = OnboardingManager(this)

        setContent {
            RetroSavingsChallengeTheme(darkTheme = true) {
                val navController = rememberNavController()
                val startDestination = if (onboardingManager.hasCompletedOnboarding) {
                    Routes.DASHBOARD
                } else {
                    Routes.ONBOARDING
                }

                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavHost(
                        navController = navController,
                        startDestination = startDestination,
                        viewModelFactory = viewModelFactory,
                        onOnboardingFinished = {
                            onboardingManager.hasCompletedOnboarding = true
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetroSavingsChallengeTheme(darkTheme = true) {
        // Previewing the whole nav host is complex.
        // It's better to preview individual screens.
        // This preview can be removed or adapted if needed.
        Text("App Preview (See Screen Previews)", color = Color.White)
    }
}
