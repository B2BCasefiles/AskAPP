package com.example.retro_savings_challenge.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.retro_savings_challenge.ui.ViewModelFactory
import com.example.retro_savings_challenge.ui.onboarding.OnboardingScreen
import com.example.retro_savings_challenge.ui.screens.AddTransactionScreen
import com.example.retro_savings_challenge.ui.screens.ChallengeBrowserScreen
import com.example.retro_savings_challenge.ui.screens.ChallengeDetailsScreen
import com.example.retro_savings_challenge.ui.screens.DashboardScreen
import com.example.retro_savings_challenge.ui.screens.RewardsScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    viewModelFactory: ViewModelFactory,
    onOnboardingFinished: () -> Unit,
    sharedTransitionScope: SharedTransitionScope
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onOnboardingFinished = {
                    onOnboardingFinished()
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.DASHBOARD) {
            DashboardScreen(
                factory = viewModelFactory,
                onNavigateToChallengeBrowser = { navController.navigate(Routes.CHALLENGE_BROWSER) },
                onNavigateToAddTransaction = { navController.navigate(Routes.ADD_TRANSACTION) },
                onNavigateToRewards = { navController.navigate(Routes.REWARDS) }
            )
        }
        composable(Routes.CHALLENGE_BROWSER) {
            ChallengeBrowserScreen(
                factory = viewModelFactory,
                onNavigateToDetails = { challengeId ->
                    navController.navigate(Routes.challengeDetails(challengeId))
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this
            )
        }
        composable(
            route = Routes.CHALLENGE_DETAILS,
            arguments = listOf(navArgument("challengeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val challengeId = backStackEntry.arguments?.getString("challengeId")
            if (challengeId != null) {
                ChallengeDetailsScreen(
                    challengeId = challengeId,
                    factory = viewModelFactory,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedVisibilityScope = this
                )
            }
        }
        composable(Routes.ADD_TRANSACTION) {
            AddTransactionScreen(
                factory = viewModelFactory,
                onSaveComplete = { navController.popBackStack() }
            )
        }
        composable(Routes.REWARDS) {
            RewardsScreen()
        }
    }
}
