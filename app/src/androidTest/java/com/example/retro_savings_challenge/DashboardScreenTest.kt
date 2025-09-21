package com.example.retro_savings_challenge

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.retro_savings_challenge.ui.screens.DashboardScreen
import com.example.retro_savings_challenge.ui.theme.RetroSavingsChallengeTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dashboardScreen_displaysCorrectly() {
        // Start the app
        composeTestRule.setContent {
            RetroSavingsChallengeTheme {
                DashboardScreen()
            }
        }

        composeTestRule.onNodeWithText("My Dashboard").assertExists()
        composeTestRule.onNodeWithText("Active Challenges").assertExists()

        // Also check for one of the dummy challenges
        composeTestRule.onNodeWithText("52-Week Savings Challenge").assertExists()
    }
}
