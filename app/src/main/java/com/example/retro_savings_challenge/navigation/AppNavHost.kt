package com.example.retro_savings_challenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.retro_savings_challenge.ui.ViewModelFactory
import com.example.retro_savings_challenge.ui.onboarding.OnboardingScreen
import com.example.retro_savings_challenge.ui.screens.ChallengeBrowserScreen
import com.example.retro_savings_challenge.ui.screens.ChallengeDetailsScreen
import com.example.retro_savings_challenge.ui.screens.DashboardScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    viewModelFactory: ViewModelFactory,
    onOnboardingFinished: () -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onOnboardingFinished = {
                    onOnboardingFinished() // Mark onboarding as complete
                    navController.navigate(Routes.DASHBOARD) {
                        // Pop up to the start destination of the graph to remove onboarding from back stack
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                factory = viewModelFactory,
                onNavigateToChallengeBrowser = { navController.navigate(Routes.CHALLENGE_BROWSER) }
            )
        }
        composable(Routes.CHALLENGE_BROWSER) {
            ChallengeBrowserScreen(
                factory = viewModelFactory,
                onNavigateToDetails = { challengeId ->
                    navController.navigate(Routes.challengeDetails(challengeId))
                }
            )
        }
        composable(
            route = Routes.CHALLENGE_DETAILS,
            arguments = listOf(navArgument("challengeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val challengeId = backStackEntry.arguments?.getString("challengeId")
            if (challengeId != null) {
                ChallengeDetailsScreen(challengeId = challengeId, factory = viewModelFactory)
            }
        }
    }
}
