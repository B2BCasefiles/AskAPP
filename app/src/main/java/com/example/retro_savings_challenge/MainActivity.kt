package com.example.retro_savings_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.retro_savings_challenge.data.preferences.OnboardingManager
import com.example.retro_savings_challenge.navigation.AppNavHost
import com.example.retro_savings_challenge.navigation.Routes
import com.example.retro_savings_challenge.ui.ViewModelFactory
import com.example.retro_savings_challenge.ui.screens.DashboardViewModel
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme

import androidx.compose.animation.ExperimentalSharedTransitionApi

@OptIn(ExperimentalSharedTransitionApi::class)
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
                    androidx.compose.animation.SharedTransitionLayout {
                        AppNavHost(
                            navController = navController,
                            startDestination = startDestination,
                            viewModelFactory = viewModelFactory,
                            onOnboardingFinished = {
                                onboardingManager.hasCompletedOnboarding = true
                            },
                            sharedTransitionScope = this
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetroSavingsChallengeTheme(darkTheme = true) {
        Text("App Preview (See Screen Previews)", color = Color.White)
    }
}
